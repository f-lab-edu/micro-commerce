package com.microcommerce.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

    public String createToken(String subject, Map<String, String> data) {
        Claims claims = Jwts.claims()
                .subject(subject)
                .add(data)
                .build();

        Date now = new Date();
        SecretKey secretKey = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET_KEY.getBytes());

        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

}
