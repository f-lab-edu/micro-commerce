package com.microcommerce.orderconsumer.infrastructure.feign;

import com.microcommerce.orderconsumer.domain.dto.feign.req.PaymentReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "micro-commerce-payment")
public interface PaymentClient {

    @PostMapping(value = "/internal-api/v1/payment")
    ResponseEntity<String> pay(@RequestBody PaymentReqDto req);

}
