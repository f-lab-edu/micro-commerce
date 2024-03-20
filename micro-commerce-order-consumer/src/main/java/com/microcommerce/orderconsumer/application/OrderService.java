package com.microcommerce.orderconsumer.application;

import com.microcommerce.orderconsumer.domain.dto.feign.req.DecreaseStockReqDto;
import com.microcommerce.orderconsumer.domain.dto.feign.req.PaymentReqDto;
import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import com.microcommerce.orderconsumer.domain.entity.OrderBasic;
import com.microcommerce.orderconsumer.domain.entity.OrderDetail;
import com.microcommerce.orderconsumer.domain.enums.OrderDetailStatus;
import com.microcommerce.orderconsumer.domain.vo.OrderDetailVo;
import com.microcommerce.orderconsumer.domain.vo.OrderVo;
import com.microcommerce.orderconsumer.infrastructure.feign.PaymentClient;
import com.microcommerce.orderconsumer.infrastructure.feign.ProductClient;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderDetailRepository;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderRepository;
import com.microcommerce.orderconsumer.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductClient productClient;

    private final PaymentClient paymentClient;

    private final OrderMapper orderMapper;

    public void order(final OrderVo vo) {
        // 주문 들어온 상품의 정보를 상품 서비스로 받아와서 Map 형태로 저장해둠
        final ResponseEntity<List<ProductResDto>> productInfoRes = productClient.getProducts(
                vo.products().stream().map(OrderDetailVo::productId).toList()
        );
        if (!productInfoRes.getStatusCode().is2xxSuccessful() || productInfoRes.getBody() == null) {
            return;
        }
        final Map<Long, ProductResDto> products = productInfoRes.getBody()
                .stream()
                .collect(Collectors.toMap(ProductResDto::id, p -> p));

        // 주문 기본 정보 저장
        final OrderBasic order = orderRepository.save(orderMapper.orderVoToEntity(vo));

        // 주문이 들어온 각 상품을 상세 주문으로 만들어 리스트에 저장
        final List<OrderDetail> orderDetails = vo.products().stream()
                .map(orderDetailVo -> orderMapper.orderDetailVoToEntity(
                        orderDetailVo, products.get(orderDetailVo.productId()), order.getId())
                )
                .toList();

        Long totalOrderPrice = 0L;
        for (OrderDetail od : orderDetails) {
            // 상품 서비스에서 재고 차감 API 호출
            final ResponseEntity<String> decStockRes = productClient.decreaseStock(
                    od.getProductId(), new DecreaseStockReqDto(vo.txId(), od.getQuantity())
            );

            // 재고 차감 API 실패 시 처리
            if (!decStockRes.getStatusCode().is2xxSuccessful() || decStockRes.getBody() == null) {
                od.setStatus(OrderDetailStatus.SYSTEM_ERROR);
                continue;
            }

            // 재고 부족 시 처리
            if ("INSUFFICIENT_STOCK".equals(decStockRes.getBody())) {
                od.setStatus(OrderDetailStatus.INSUFFICIENT_STOCK);
                continue;
            }

            // 재고 성공적으로 차감된 상품만 주문 처리
            totalOrderPrice += od.getPrice();
        }

        // 주문 금액 차감 API
        final ResponseEntity<String> payRes = paymentClient.pay(new PaymentReqDto(vo.userId(), totalOrderPrice, vo.txId()));
        // 주문 금액 요청이 실패했을 경우
        if (!payRes.getStatusCode().is2xxSuccessful() || payRes.getBody() == null) {
            orderDetails.forEach(od -> od.setStatus(OrderDetailStatus.SYSTEM_ERROR));
        }
        // 주문 금액이 부족한 경우
        else if ("INSUFFICIENT_POINT".equals(payRes.getBody())) {
            orderDetails.forEach(od -> od.setStatus(OrderDetailStatus.INSUFFICIENT_POINT));
        }

        // 상세 주문 저장
        orderDetailRepository.saveAll(orderDetails);
    }

}
