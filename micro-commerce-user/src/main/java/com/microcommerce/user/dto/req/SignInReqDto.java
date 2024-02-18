package com.microcommerce.user.dto.req;

import com.microcommerce.user.domain.enums.UserType;

public record SignInReqDto(String email, String password, UserType type) {
}
