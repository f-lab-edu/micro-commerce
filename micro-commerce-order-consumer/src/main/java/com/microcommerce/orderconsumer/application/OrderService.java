package com.microcommerce.orderconsumer.application;

import com.microcommerce.orderconsumer.domain.dto.feign.req.PaymentReqDto;
import com.microcommerce.orderconsumer.domain.entity.OrderBasic;
import com.microcommerce.orderconsumer.domain.entity.OrderDetail;
import com.microcommerce.orderconsumer.domain.enums.OrderDetailStatus;
import com.microcommerce.orderconsumer.domain.vo.OrderVo;
import com.microcommerce.orderconsumer.exception.OrderException;
import com.microcommerce.orderconsumer.exception.OrderExceptionCode;
import com.microcommerce.orderconsumer.infrastructure.feign.PaymentClient;
import com.microcommerce.orderconsumer.infrastructure.feign.ProductClient;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderDetailRepository;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderRepository;
import com.microcommerce.orderconsumer.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        // 같은 사용자가 3초 동안 두번 이상의 주문을 넣으면 거절
        if (orderRepository.existsByBuyerIdAndCreatedAtAfter(vo.userId(), LocalDateTime.now().minusSeconds(3))) {
            // TODO: 알람
            throw new OrderException(OrderExceptionCode.TOO_MANY_ORDER);
        }

        int totalOrderPrice = vo.products()
                .stream()
                .mapToInt(p -> p.quantity() * p.productPrice())
                .sum();

        ResponseEntity<Integer> balanceRes = paymentClient.getUserBalance(vo.userId());
        // 재고 차감 API 실패 시 처리
        if (!balanceRes.getStatusCode().is2xxSuccessful() || balanceRes.getBody() == null) {
            return;
        }
        if (totalOrderPrice > balanceRes.getBody()) {
            return;
        }

        // 주문 기본 정보 저장
        final OrderBasic order = orderRepository.save(orderMapper.orderVoToEntity(vo));

        // 주문이 들어온 각 상품을 상세 주문으로 만들어 리스트에 저장
        final List<OrderDetail> orderDetails = vo.products().stream()
                .map(p -> orderMapper.orderDetailVoToEntity(p, order.getId(), p.productPrice() * p.quantity()))
                .toList();

        Long paymentPrice = 0L;
        for (final OrderDetail od : orderDetails) {
            // 상품 서비스에서 재고 차감 API 호출
            final ResponseEntity<String> decStockRes = productClient.changeStock(
                    od.getProductId(), od.getQuantity() * -1
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
            paymentPrice += od.getPrice();
            od.setStatus(OrderDetailStatus.ORDER_COMPLETE);
        }

        // 주문 금액 차감 API
        final ResponseEntity<String> payRes = paymentClient.pay(
                vo.userId(),
                new PaymentReqDto(vo.userId(), paymentPrice * -1, "PAYMENT", vo.txId())
        );

        // 주문 금액 차감에 실패했을 경우
        if (!payRes.getStatusCode().is2xxSuccessful()) {
            log.error(payRes.toString());

            // 이미 차감된 재고가 있는 상태에서 주문이 실패하면 차감한 재고를 다시 채워줌
            orderDetails.stream()
                    .filter(o -> o.getStatus() == OrderDetailStatus.ORDER_COMPLETE)
                    .forEach(o -> productClient.changeStock(o.getProductId(), Math.abs(o.getQuantity())));

            // 모든 상세 주문 실패 처리
            if ("INSUFFICIENT_BALANCE".equals(payRes.getBody())) {
                orderDetails.forEach(od -> od.setStatus(OrderDetailStatus.INSUFFICIENT_BALANCE));
            } else {
                orderDetails.forEach(od -> od.setStatus(OrderDetailStatus.SYSTEM_ERROR));
            }
        }

        // 상세 주문 저장
        orderDetailRepository.saveAll(orderDetails);
    }

}
