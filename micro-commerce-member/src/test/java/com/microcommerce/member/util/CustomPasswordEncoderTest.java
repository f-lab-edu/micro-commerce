package com.microcommerce.member.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

 @ExtendWith(SpringExtension.class)
class CustomPasswordEncoderTest {

    @SpyBean
    private CustomPasswordEncoder passwordEncoder;

    @DisplayName("비밀번호 암호화 테스트")
    @Test
    public void passwordEncode() {
        final String rawPassword = "rawPassword_rawPassword_rawPassword";
        final String invalidPassword = "invalidPassword_invalidPassword_invalidPassword";
        final String encPassword = passwordEncoder.encode(rawPassword);

        assertThat(passwordEncoder.matches(rawPassword, encPassword)).isTrue();
        assertThat(passwordEncoder.matches(invalidPassword, encPassword)).isFalse();
    }

}