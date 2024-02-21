package com.microcommerce.member.domain.dto.req;

import com.microcommerce.member.domain.enums.MemberType;

public record SignInReqDto(String email, String password, MemberType type) {
}
