package com.microcommerce.apigateway.infrastructure.feign;

import com.microcommerce.apigateway.domain.dto.res.UserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Primary
@FeignClient(name = "micro-commerce-member")
public interface MemberClient {

    // TODO: [질문] fallback 처리
    @PostMapping(value = "/api/v1/auth")
    UserData getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader);

}
