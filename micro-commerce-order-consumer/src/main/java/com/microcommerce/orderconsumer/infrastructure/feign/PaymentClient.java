package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.req.PaymentReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Primary
@FeignClient(name = "micro-commerce-payment", fallbackFactory = PaymentClientFallbackFactory.class)
public interface PaymentClient {

    @PostMapping(value = "/api/v1/payments")
    ResponseEntity<String> pay(@RequestHeader("x-user-id") Long userId, @RequestBody PaymentReqDto req);

    @GetMapping(value = "/api/v1/payments/balance")
    ResponseEntity<Integer> getUserBalance(@RequestHeader("x-user-id") @RequestParam("user") Long userId);

}
