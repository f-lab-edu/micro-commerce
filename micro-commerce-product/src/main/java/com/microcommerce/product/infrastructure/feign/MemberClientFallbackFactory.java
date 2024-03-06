package com.microcommerce.product.infrastructure.feign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberClientFallbackFactory implements FallbackFactory<MemberClient> {

    private final MemberClientFallback memberClientFallback;

    @Override
    public MemberClient create(Throwable cause) {
        log.info("error occurred, {}", cause.getMessage());
        return memberClientFallback;
    }

}
