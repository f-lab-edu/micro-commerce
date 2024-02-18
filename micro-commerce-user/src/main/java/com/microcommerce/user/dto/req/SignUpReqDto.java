package com.microcommerce.user.dto.req;

import com.microcommerce.user.domain.enums.UserType;

public record SignUpReqDto(String email,

                           String name,

                           String password,

                           String phoneNumber,

                           UserType type) {
}
