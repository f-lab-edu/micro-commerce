package com.microcommerce.member.application;

import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.domain.enums.MemberType;
import com.microcommerce.member.infrastructure.repository.MemberRepository;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
import com.microcommerce.member.util.CustomPasswordEncoder;
import com.microcommerce.member.util.JwtUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberServiceImpl memberService;

    @Mock
    CustomPasswordEncoder passwordEncoder;

    @MockBean
    JwtUtil jwtUtil;

    @Mock
    MemberRepository memberRepository;

    @DisplayName("회원가입 테스트")
    @Test
    void signup() {
        SignUpReqDto req = new SignUpReqDto("test@test.com", "테스트 유저", "password123", "01000000000", MemberType.NORMAL);

        Member newMember = Member.getInstance("test@test.com", "password123", "테스트 유저", "01000000000", MemberType.NORMAL);
        ReflectionTestUtils.setField(newMember, "id", 1L);

        Mockito.when(memberRepository.existsByEmailAndType(req.email(), req.type())).thenReturn(false);
        Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(newMember);

        SignUpResDto res = memberService.signUp(req);
        assertThat(res.email()).isEqualTo(req.email());
    }


}