package com.microcommerce.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderExceptionCode {

    UNAUTHORIZED(
            HttpStatus.UNAUTHORIZED, "UNAUTHORIZED",
            "잘못된 접근 권한"
    ),

    FORBIDDEN(
            HttpStatus.FORBIDDEN, "FORBIDDEN",
            "잘못된 접근 권한"
    ),

    BAD_REQUEST(
            HttpStatus.BAD_REQUEST, "BAD_REQUEST",
            "잘못된 요청 정보"
    ),

    USER_NOT_FOUND(
            HttpStatus.BAD_REQUEST, "USER_NOT_FOUND",
            "잘못된 접근 권한"
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
