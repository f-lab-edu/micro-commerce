package com.microcommerce.order.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public final class ApiResult<T> {

    private T data;
    private boolean success;
    private String message;

    public static <T> ApiResult<T> success(final T data) {
        return ApiResult.<T>builder()
                .data(data)
                .success(true)
                .build();
    }

    public static <T> ApiResult<T> fail(final String message) {
        return ApiResult.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

}
