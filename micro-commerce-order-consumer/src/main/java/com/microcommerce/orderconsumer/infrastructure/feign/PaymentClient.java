package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.req.PaymentReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "micro-commerce-payment")
public interface PaymentClient {

    @PostMapping(value = "/api/v1/payments")
    ResponseEntity<String> pay(@RequestHeader("x-user-id") Long userId, @RequestBody PaymentReqDto req);

}
