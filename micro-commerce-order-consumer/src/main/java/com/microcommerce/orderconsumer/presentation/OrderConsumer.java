package com.microcommerce.orderconsumer.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcommerce.orderconsumer.application.OrderService;
import com.microcommerce.orderconsumer.domain.constant.KafkaTopic;
import com.microcommerce.orderconsumer.domain.vo.kafka.OrderRecord;
import com.microcommerce.orderconsumer.exception.OrderException;
import com.microcommerce.orderconsumer.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopic.ORDER, groupId = "order-service")
    public void order(final ConsumerRecord<String, String> record, final Acknowledgment acknowledgment) {
        final OrderRecord order;
        try {
            order = objectMapper.readValue(record.value(), OrderRecord.class);
            orderService.order(orderMapper.OrderRecordToVo(order));
        } catch (final JsonProcessingException e) {
            log.error("recode parsing error: {}", e.getMessage());
        } catch (final OrderException e) {
            log.error("order error: {}", e.getMessage());
        } catch (final Exception e) {
            log.error("unknown error: {}", e.getMessage());
        } finally {
            acknowledgment.acknowledge();
        }
    }

}
