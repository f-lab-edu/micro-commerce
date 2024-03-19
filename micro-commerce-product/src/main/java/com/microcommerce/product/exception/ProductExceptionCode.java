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
    ),

    PRODUCT_NOT_FOUND(
            HttpStatus.NOT_FOUND, "PRODUCT_NOT_FOUND",
            "상품을 찾을 수 없을 경우"
    ),

    INSUFFICIENT_STOCK(
            HttpStatus.OK, "INSUFFICIENT_STOCK",
            "재고가 부족한 경우"
    ),

    EXCEED_STOCK(
            HttpStatus.OK, "EXCEED_STOCK",
            "내부 서비스 호출 시 오휴"
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
