package com.microcommerce.order.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Primary
@FeignClient(name = "micro-commerce-payment", fallbackFactory = PaymentClientFallbackFactory.class)
public interface PaymentClient {

    @GetMapping(value = "/internal-api/v1/payments/balance")
    ResponseEntity<Integer> getUserBalance(@RequestHeader("x-user-id") @RequestParam("user") Long userId);

}
