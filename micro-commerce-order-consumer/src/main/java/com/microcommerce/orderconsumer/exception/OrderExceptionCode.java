package com.microcommerce.orderconsumer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderExceptionCode {

    INTERNAL_REQUEST_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_REQUEST_ERROR",
            "내부 서비스 호출 시 오휴"
    ),

    TOO_MANY_ORDER(
            HttpStatus.BAD_REQUEST, "TOO_MANY_ORDER",
            "내부 서비스 호출 시 오휴"
    );

    private final HttpStatus status;
    private final String code;
    private final String description;

}
