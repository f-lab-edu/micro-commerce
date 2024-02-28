package com.microcommerce.member.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberExceptionCode {

    EXISTS_USER(
            HttpStatus.BAD_REQUEST, "EXISTS_USER",
            "이미 존재하는 유저 이메일"
    ),

    FORBIDDEN(
            HttpStatus.FORBIDDEN, "FORBIDDEN",
            "잘못된 접근 권한"
    ),

    INVALID_USER_INFO(
            HttpStatus.BAD_REQUEST, "INVALID_USER_INFO",
            "잘못된 로그인 정보입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
