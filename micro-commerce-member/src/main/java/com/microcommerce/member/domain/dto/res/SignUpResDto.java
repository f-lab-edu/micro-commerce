package com.microcommerce.member.domain.dto.res;

import lombok.Builder;

@Builder
public record SignUpResDto(String email) {

    public static SignUpResDto getInstance(final String email) {
        return SignUpResDto.builder()
                .email(email)
                .build();
    }

}