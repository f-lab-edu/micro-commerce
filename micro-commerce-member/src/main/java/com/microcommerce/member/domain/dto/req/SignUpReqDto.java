package com.microcommerce.member.domain.dto.req;

import com.microcommerce.member.domain.enums.MemberType;

public record SignUpReqDto(String email,

                           String name,

                           String password,

                           String phoneNumber,

                           MemberType type) {
}
