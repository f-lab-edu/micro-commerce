package com.microcommerce.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderException extends RuntimeException {

    private final OrderExceptionCode code;

}
