package com.microcommerce.product.infrastructure.feign;

import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Primary
@FeignClient(name = "micro-commerce-member", fallbackFactory = MemberClientFallbackFactory.class)
public interface MemberClient {

    @GetMapping(value = "/api/v1/members/{userId}/profile")
    ProfileResDto getProfile(@RequestHeader("x-user-id") @PathVariable("userId") Long userId);

}
