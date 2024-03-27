package com.microcommerce.order.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(final String topic, final Object message) {
        kafkaTemplate.send(topic, message);
    }

    public void send(final String topic, final String key, final Object message) {
        final CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, key, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("producer success message: {}, offset: {}",
                        result.getProducerRecord().value().toString(), result.getRecordMetadata().offset());
            } else {
                log.error("producer failure message: {}", ex.getMessage());
            }
        });
    }

}
