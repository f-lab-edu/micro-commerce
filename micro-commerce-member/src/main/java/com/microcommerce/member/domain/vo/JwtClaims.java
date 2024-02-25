package com.microcommerce.member.domain.vo;

import lombok.Builder;

@Builder
public record JwtClaims(String userId) {

    public static JwtClaims getInstance(String userId) {
        return JwtClaims.builder().userId(userId).build();
    }

}
