package com.microcommerce.order.application;

import com.microcommerce.order.domain.vo.OrderVo;
import com.microcommerce.order.domain.vo.kafka.OrderRecord;
import com.microcommerce.order.infrastructure.kafka.KafkaProducer;
import com.microcommerce.order.infrastructure.kafka.KafkaTopic;
import com.microcommerce.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final KafkaProducer kafkaProducer;

    private final OrderMapper orderMapper;

    public void order(OrderVo vo) {
        final String txId = UUID.randomUUID().toString();
        final OrderRecord record = orderMapper.toOrderRecord(vo, txId);
        kafkaProducer.send(KafkaTopic.ORDER, record);
    }

}
