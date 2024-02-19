package com.microcommerce.member.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${micro-commerce.jwt.secret-key.access-token}")
    private String ACCESS_TOKEN_SECRET_KEY;
    @Value("#{new Long(${micro-commerce.jwt.expiration-time.access-token})}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    public String createToken(final String subject, final Map<String, String> data) {
        final Claims claims = Jwts.claims()
                .subject(subject)
                .add(data)
                .build();

        final Date now = new Date();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    public Claims parseToken(final String token) {
        Jwt<JwsHeader, Claims> jwt = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        return jwt.getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET_KEY.getBytes());
    }

}
