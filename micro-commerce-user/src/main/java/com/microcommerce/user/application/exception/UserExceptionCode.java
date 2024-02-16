package com.microcommerce.user.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserExceptionCode {

    EXISTS_USER(
            HttpStatus.BAD_REQUEST,
            "EXISTS_USER",
            "이미 존재하는 유저 이메일"
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
