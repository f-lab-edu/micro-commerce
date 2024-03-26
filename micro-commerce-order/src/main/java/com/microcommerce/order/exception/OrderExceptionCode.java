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
    ),

    INSUFFICIENT_BALANCE(
            HttpStatus.BAD_REQUEST, "INSUFFICIENT_BALANCE",
            "금액이 모자름"
    ),

    INSUFFICIENT_STOCK(
            HttpStatus.BAD_REQUEST, "INSUFFICIENT_STOCK",
            "재고 부족"
    ),

    PRODUCT_NOT_FOUND(
            HttpStatus.BAD_REQUEST, "PRODUCT_NOT_FOUND",
            "상품 정보를 찾을 수 없음"
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
