package com.microcommerce.user.dto.req;

import com.microcommerce.user.domain.enums.UserType;

public record SignupReqDto(String email,

                           String name,

                           String password,

                           String phoneNumber,

                           UserType type) {
}
