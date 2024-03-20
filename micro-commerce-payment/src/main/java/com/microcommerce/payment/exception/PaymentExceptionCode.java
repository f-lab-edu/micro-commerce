package com.microcommerce.payment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PaymentExceptionCode {

    UNAUTHORIZED(
            HttpStatus.UNAUTHORIZED, "UNAUTHORIZED",
            "미인증"
    ),

    FORBIDDEN(
            HttpStatus.FORBIDDEN, "FORBIDDEN",
            "잘못된 접근 권한"
    ),

    USER_NOT_FOUND(
            HttpStatus.NOT_FOUND, "USER_NOT_FOUND",
            "회원 정보를 찾을 수 없음"
    ),

    WALLET_NOT_FOUND(
            HttpStatus.NOT_FOUND, "WALLET_NOT_FOUND",
            "지갑 정보를 찾을 수 없을 경우"
    ),

    INSUFFICIENT_BALANCE(
            HttpStatus.BAD_REQUEST, "INSUFFICIENT_BALANCE",
            "잔고가 부족한 경우"
    ),

    DUPLICATE_REQUEST(
            HttpStatus.BAD_REQUEST, "DUPLICATE_REQUEST",
            "중복된 요청입니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
