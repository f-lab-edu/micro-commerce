package com.microcommerce.member.util;

import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.domain.vo.JwtClaims;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = {JwtUtil.class}) // @ExtendWith(SpringExtension.class)을 포함한다.
class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        Map<String, String> data = Map.of("email", "test@test.com");
        this.token = jwtUtil.createToken("1", data);
    }

    @DisplayName("jwt 토큰 생성 테스트")
    @Test
    public void createToken() {
        assertThat(token).isNotNull().isNotEmpty();
    }

    @DisplayName("jwt 토큰 유효성 테스트")
    @Test
    public void parseToken() {
        JwtClaims claims = jwtUtil.parseToken(token);
        assertThat(claims.userId()).isEqualTo("1");

    }

}