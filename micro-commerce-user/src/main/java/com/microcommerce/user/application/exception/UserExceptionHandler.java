package com.microcommerce.user.application.exception;

import com.microcommerce.user.dto.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
// TODO: RestControllerAdvice 동작 방식 알아보기
@RestControllerAdvice
public class UserExceptionHandler {

    // TODO: ExceptionHandler 동작 방식 알아보기
    @ExceptionHandler({UserException.class})
    public ResponseEntity<ApiResult<?>> handlerUserException(UserException ex, HttpServletRequest req) {
        UserExceptionCode code = ex.getCode();

        log.info("[UserException] URI: {}, METHOD: {}, MESSAGE: {}, status: {}",
                req.getRequestURI(), req.getMethod(), code.getDescription(), code.getStatus().toString());

        return ResponseEntity.status(code.getStatus()).body(ApiResult.fail(code.getCode()));
    }

}