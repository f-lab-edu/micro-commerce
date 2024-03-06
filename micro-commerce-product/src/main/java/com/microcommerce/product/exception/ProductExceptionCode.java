package com.microcommerce.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductExceptionCode {

    UNAUTHORIZED(
            HttpStatus.UNAUTHORIZED, "UNAUTHORIZED",
            "잘못된 접근 권한"
    ),

    FORBIDDEN(
            HttpStatus.FORBIDDEN, "FORBIDDEN",
            "잘못된 접근 권한"
    ),

    USER_NOT_FOUND(
            HttpStatus.BAD_REQUEST, "USER_NOT_FOUND",
            "잘못된 접근 권한"
    ),

    INTERNAL_REQUEST_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_REQUEST_ERROR",
            "내부 서비스 호출 시 오휴"
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
