package com.microcommerce.orderconsumer.application;

import com.microcommerce.orderconsumer.domain.dto.feign.req.DecreaseStockReqDto;
import com.microcommerce.orderconsumer.domain.dto.feign.res.DecreaseStockResDto;
import com.microcommerce.orderconsumer.domain.dto.feign.res.ProductResDto;
import com.microcommerce.orderconsumer.domain.entity.OrderBasic;
import com.microcommerce.orderconsumer.domain.entity.OrderDetail;
import com.microcommerce.orderconsumer.domain.enums.OrderDetailStatus;
import com.microcommerce.orderconsumer.domain.vo.OrderDetailVo;
import com.microcommerce.orderconsumer.domain.vo.OrderVo;
import com.microcommerce.orderconsumer.domain.vo.kafka.PayPointRecord;
import com.microcommerce.orderconsumer.infrastructure.feign.ProductClient;
import com.microcommerce.orderconsumer.infrastructure.kafka.KafkaProducer;
import com.microcommerce.orderconsumer.infrastructure.kafka.KafkaTopic;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderDetailRepository;
import com.microcommerce.orderconsumer.infrastructure.repository.OrderRepository;
import com.microcommerce.orderconsumer.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final OrderMapper orderMapper;

    private final KafkaProducer kafkaProducer;

    @Transactional
    public void order(final OrderVo vo) {
        final Map<Long, ProductResDto> products = productClient.getProducts(
                vo.products().stream().map(OrderDetailVo::productId).toList()
        ).stream().collect(Collectors.toMap(ProductResDto::id, p -> p));

        final OrderBasic order = orderRepository.save(orderMapper.orderVoToEntity(vo));

        final List<OrderDetail> orderDetails = vo.products().stream()
                .map(orderMapper::orderDetailVoToEntity)
                .peek(od -> {
                    final ProductResDto info = products.get(od.getProductId());
                    od.setProductInfo(info.name(), info.imageUrl(), info.price(), info.sellerName());
                    od.setOrderId(order.getId());
                })
                .toList();

        // FIXME
        for (OrderDetail od : orderDetails) {
            // 재고 차감
            final DecreaseStockResDto res = productClient.decreaseStock(
                    od.getProductId(), new DecreaseStockReqDto(od.getQuantity())
            );
            if (!res.isSuccess()) {
                od.setStatus(OrderDetailStatus.ORDER_REJECTED);
                continue;
            }

            // 주문 금액 차감
            kafkaProducer.send(
                    KafkaTopic.PAYMENT_POINT,
                    new PayPointRecord(vo.userId(), od.getPrice())
            );
        }
        orderDetailRepository.saveAll(orderDetails);
    }

}
