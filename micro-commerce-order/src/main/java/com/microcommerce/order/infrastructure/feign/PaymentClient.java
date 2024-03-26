package com.microcommerce.order.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name = "micro-commerce-payment", fallbackFactory = PaymentClientFallbackFactory.class)
public interface PaymentClient {

    @GetMapping(value = "/api/v1/payments/balance")
    ResponseEntity<Integer> getUserBalance(@RequestHeader("x-user-id") @RequestParam("user") Long userId);

}
