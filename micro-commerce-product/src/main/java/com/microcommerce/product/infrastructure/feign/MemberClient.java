package com.microcommerce.product.infrastructure.feign;

import com.microcommerce.product.domain.dto.ApiResult;
import com.microcommerce.product.domain.dto.feign.res.ProfileResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("micro-commerce-member")
public interface MemberClient {

    @GetMapping(value = "/api/v1/members/{userId}/profile")
    ApiResult<ProfileResDto> getProfile(@RequestHeader("x-user-id") @PathVariable("userId") Long userId);

}
