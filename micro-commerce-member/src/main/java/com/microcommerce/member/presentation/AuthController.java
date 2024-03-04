package com.microcommerce.member.presentation;

import com.microcommerce.member.domain.dto.res.AuthResDto;
import com.microcommerce.member.domain.vo.JwtClaims;
import com.microcommerce.member.exception.MemberException;
import com.microcommerce.member.exception.MemberExceptionCode;
import com.microcommerce.member.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/auth")
    public AuthResDto authenticate(@RequestHeader final HttpHeaders header) {
        final String authorizationHeader = header.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            throw new MemberException(MemberExceptionCode.UNAUTHORIZED);
        }
        final String jwt = jwtUtil.extractToken(authorizationHeader);

        final JwtClaims claims;
        try {
            claims = jwtUtil.parseToken(jwt);
        } catch (JwtException je) {
            log.error(je.getMessage());
            throw new MemberException(MemberExceptionCode.UNAUTHORIZED);
        }

        return AuthResDto.getInstance(claims.userId());
    }

}
