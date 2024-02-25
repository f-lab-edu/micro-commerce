package com.microcommerce.member.presentation;

import com.microcommerce.member.domain.dto.ApiResult;
import com.microcommerce.member.domain.dto.res.AuthResDto;
import com.microcommerce.member.domain.vo.JwtClaims;
import com.microcommerce.member.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/api/v1/auth")
    public ResponseEntity<ApiResult<AuthResDto>> authenticate(final @RequestHeader HttpHeaders header) {
        final String authorizationHeader = header.getFirst(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResult.fail(HttpStatus.UNAUTHORIZED.name()));
        }
        final String jwt = jwtUtil.extractToken(authorizationHeader);

        final JwtClaims claims;
        try {
            claims = jwtUtil.parseToken(jwt);
        } catch (JwtException je) {
            log.error(je.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResult.fail(HttpStatus.UNAUTHORIZED.name()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.success(AuthResDto.getInstance(claims.userId())));
    }

}
