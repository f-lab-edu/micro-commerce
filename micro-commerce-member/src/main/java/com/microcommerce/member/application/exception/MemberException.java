package com.microcommerce.member.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException {

    private final MemberExceptionCode code;

}
