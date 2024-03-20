package com.microcommerce.payment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentException extends RuntimeException {

    private final PaymentExceptionCode code;

}
