package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.req.PaymentReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentClientFallback implements PaymentClient{
    @Override
    public ResponseEntity<String> pay(Long userId, PaymentReqDto req) {
        log.error("pay failed");
        return null;
    }

    @Override
    public ResponseEntity<Integer> getUserBalance(Long userId) {
        log.error("getUserBalance failed");
        return null;
    }
}
