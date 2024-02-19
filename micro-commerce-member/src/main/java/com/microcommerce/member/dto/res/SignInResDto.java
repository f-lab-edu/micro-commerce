package com.microcommerce.member.dto.res;

import lombok.Builder;

@Builder
public record SignInResDto(String accessToken) {

    public static SignInResDto getInstance(final String accessToken) {
        return SignInResDto.builder()
                .accessToken(accessToken)
                .build();
    }

}
