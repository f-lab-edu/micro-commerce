package com.microcommerce.order.infrastructure.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentClientFallback implements PaymentClient {
    @Override
    public ResponseEntity<Integer> getUserBalance(Long userId) {
        log.error("getUserBalance failed");
        return null;
    }
}
