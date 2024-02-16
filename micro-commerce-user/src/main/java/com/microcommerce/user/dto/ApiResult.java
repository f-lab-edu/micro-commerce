package com.microcommerce.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class ApiResult<T> {

    private T data;
    private boolean isSuccess;
    private String message;

    public static <T> ApiResult<T> success(T data) {
        return ApiResult.<T>builder()
                .data(data)
                .isSuccess(true)
                .build();
    }

    public static <T> ApiResult<T> fail(String message) {
        return ApiResult.<T>builder()
                .isSuccess(false)
                .message(message)
                .build();
    }

}
