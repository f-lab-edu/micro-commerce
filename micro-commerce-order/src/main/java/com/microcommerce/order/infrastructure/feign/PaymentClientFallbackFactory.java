package com.microcommerce.order.infrastructure.feign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PaymentClientFallbackFactory implements FallbackFactory<PaymentClient> {

    private final PaymentClientFallback paymentClientFallback;

    @Override
    public PaymentClient create(Throwable cause) {
        log.info("error occurred, {}", cause.getMessage());
        return paymentClientFallback;
    }
}
