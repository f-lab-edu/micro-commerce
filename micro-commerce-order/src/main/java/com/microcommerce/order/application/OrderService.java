package com.microcommerce.order.application;

import com.microcommerce.order.domain.vo.OrderVo;
import com.microcommerce.order.domain.vo.kafka.OrderRecord;
import com.microcommerce.order.infrastructure.kafka.KafkaProducer;
import com.microcommerce.order.infrastructure.kafka.KafkaTopic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final KafkaProducer kafkaProducer;

    public void order(OrderVo vo) {
        final OrderRecord record = OrderRecord.getInstance(vo);
        kafkaProducer.send(KafkaTopic.ORDER, record);
    }

}
