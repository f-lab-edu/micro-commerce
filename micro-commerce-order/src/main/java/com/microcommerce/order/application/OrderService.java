package com.microcommerce.order.application;

import com.microcommerce.order.domain.dao.PaymentClientDao;
import com.microcommerce.order.domain.dao.ProductClientDao;
import com.microcommerce.order.domain.dto.feign.res.ProductResDto;
import com.microcommerce.order.domain.vo.OrderDetailVo;
import com.microcommerce.order.domain.vo.OrderVo;
import com.microcommerce.order.domain.vo.kafka.OrderRecord;
import com.microcommerce.order.exception.OrderException;
import com.microcommerce.order.exception.OrderExceptionCode;
import com.microcommerce.order.infrastructure.kafka.KafkaProducer;
import com.microcommerce.order.infrastructure.kafka.KafkaTopic;
import com.microcommerce.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final KafkaProducer kafkaProducer;

    private final OrderMapper orderMapper;

    private final PaymentClientDao paymentClientDao;

    private final ProductClientDao productClientDao;

    public void order(final OrderVo vo) {
        final Map<Long, ProductResDto> productMap = productClientDao.getProductMapByIds(
                vo.products().stream()
                        .map(OrderDetailVo::productId)
                        .toList()
        );

        final int totalOrderInfo = vo.products()
                .stream()
                .mapToInt(p -> p.quantity() * productMap.get(p.productId()).price())
                .sum();

        final int balance = paymentClientDao.getUserBalance(vo.userId());
        if (balance < totalOrderInfo) {
            throw new OrderException(OrderExceptionCode.INSUFFICIENT_BALANCE);
        }

        // 주문 상품 중 재고 부족인 상품이 있으면 전체 주문 취소
        vo.products().forEach(
                p -> {
                    if (productMap.get(p.productId()).stock() < p.quantity()) {
                        throw new OrderException(OrderExceptionCode.INSUFFICIENT_STOCK);
                    }
                }
        );

        final OrderRecord record = orderMapper.toOrderRecord(
                vo,
                UUID.randomUUID().toString(),
                vo.products()
                        .stream()
                        .map(p -> orderMapper.toOrderDetailRecord(p, productMap.get(p.productId())))
                        .toList()
        );

        kafkaProducer.send(KafkaTopic.ORDER, vo.userId().toString(), record);
    }

}
