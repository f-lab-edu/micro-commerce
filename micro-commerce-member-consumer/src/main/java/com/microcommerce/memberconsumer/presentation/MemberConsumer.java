package com.microcommerce.memberconsumer.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microcommerce.memberconsumer.application.MemberService;
import com.microcommerce.memberconsumer.domain.constant.KafkaTopic;
import com.microcommerce.memberconsumer.domain.dto.kafka.PaymentPointRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberConsumer {

    private final MemberService memberService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopic.PAYMENT_POINT, groupId = "member-consumer-service")
    public void payment(final ConsumerRecord<String, String> record) {
        final PaymentPointRecord msg;
        try {
            msg = objectMapper.readValue(record.value(), PaymentPointRecord.class);
        } catch (JsonProcessingException e) {
            log.error("recode parsing error: {}", e.getMessage());
            return;
        }

        memberService.payment(msg.userId(), msg.price());
    }

}
