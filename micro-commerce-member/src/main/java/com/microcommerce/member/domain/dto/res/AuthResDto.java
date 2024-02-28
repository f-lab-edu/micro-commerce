package com.microcommerce.member.domain.dto.res;

import lombok.Builder;

@Builder
public record AuthResDto (String userId) {

    public static AuthResDto getInstance(String userId) {
        return AuthResDto.builder().userId(userId).build();
    }

}
