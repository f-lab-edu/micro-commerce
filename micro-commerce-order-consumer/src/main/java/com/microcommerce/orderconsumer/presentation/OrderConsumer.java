package com.microcommerce.orderconsumer.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcommerce.orderconsumer.application.OrderService;
import com.microcommerce.orderconsumer.domain.constant.KafkaTopic;
import com.microcommerce.orderconsumer.domain.vo.OrderVo;
import com.microcommerce.orderconsumer.domain.vo.kafka.OrderRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final OrderService orderService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopic.ORDER, groupId = "order-service")
    public void order(final ConsumerRecord<String, String> record){
        final OrderRecord order;
        try {
            order = objectMapper.readValue(record.value(), OrderRecord.class);
        } catch (JsonProcessingException e) {
            log.error("recode parsing error: {}", e.getMessage());
            return;
        }

        orderService.order(OrderVo.getInstance(order));
    }

}
