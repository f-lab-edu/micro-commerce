package com.microcommerce.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductException extends RuntimeException {

    private final ProductExceptionCode code;

}
