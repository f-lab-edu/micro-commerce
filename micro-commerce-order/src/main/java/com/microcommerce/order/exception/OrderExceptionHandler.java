package com.microcommerce.order.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// TODO: RestControllerAdvice 동작 방식 알아보기
@RestControllerAdvice
public class OrderExceptionHandler {

    // TODO: ExceptionHandler 동작 방식 알아보기
    @ExceptionHandler({OrderException.class})
    public ResponseEntity<String> handlerUserException(final OrderException ex, final HttpServletRequest req) {
        final OrderExceptionCode code = ex.getCode();

        log.info("[OrderException] URI: {}, METHOD: {}, MESSAGE: {}, status: {}",
                req.getRequestURI(), req.getMethod(), code.getDescription(), code.getStatus().toString());

        return ResponseEntity.status(code.getStatus()).body(code.getCode());
    }

}
