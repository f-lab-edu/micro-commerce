package com.microcommerce.member.domain.entity;

import com.microcommerce.member.domain.enums.MemberType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @DisplayName("회원 생성 테스트")
    @Test
    void createUser() {
        Member newMember = Member.getInstance("test@test.com", "hashed password example", "테스트 유저", "01000000000", MemberType.NORMAL);

        assertThat(newMember.getEmail()).isEqualTo("test@test.com");
        assertThat(newMember.getPassword()).isEqualTo("hashed password example");
        assertThat(newMember.getName()).isEqualTo("테스트 유저");
        assertThat(newMember.getPhoneNumber()).isEqualTo("01000000000");
        assertThat(newMember.getType()).isEqualTo(MemberType.NORMAL);
    }

}