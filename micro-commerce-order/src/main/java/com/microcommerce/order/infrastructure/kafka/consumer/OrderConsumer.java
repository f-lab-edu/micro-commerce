package com.microcommerce.order.infrastructure.kafka.consumer;

import com.microcommerce.order.infrastructure.kafka.KafkaTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

// FIXME: consumer 파일 위치
@Slf4j
@Component
public class OrderConsumer {

    @KafkaListener(topics = KafkaTopic.ORDER, groupId = "order-service")
    public void order(ConsumerRecord<String, Object> message){
        log.debug(message.toString());
        log.debug(message.value().toString());
    }

}
