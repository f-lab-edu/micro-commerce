package com.microcommerce.member.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public final class ApiResult<T> {

    private T data;
    private boolean isSuccess;
    private String message;

    public static <T> ApiResult<T> success(final T data) {
        return ApiResult.<T>builder()
                .data(data)
                .isSuccess(true)
                .build();
    }

    public static <T> ApiResult<T> fail(final String message) {
        return ApiResult.<T>builder()
                .isSuccess(false)
                .message(message)
                .build();
    }

}
