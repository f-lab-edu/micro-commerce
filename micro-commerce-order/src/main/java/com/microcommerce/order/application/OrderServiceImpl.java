package com.microcommerce.order.application;

import com.microcommerce.order.domain.vo.OrderVo;
import com.microcommerce.order.infrastructure.kafka.KafkaProducer;
import com.microcommerce.order.infrastructure.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final KafkaProducer kafkaProducer;

    @Override
    public void order(OrderVo data) {
        kafkaProducer.send(KafkaTopic.ORDER, data);
    }
}
