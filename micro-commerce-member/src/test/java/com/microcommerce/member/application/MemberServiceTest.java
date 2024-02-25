package com.microcommerce.member.application;

import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.domain.enums.MemberType;
import com.microcommerce.member.exception.MemberException;
import com.microcommerce.member.infrastructure.repository.MemberRepository;
import com.microcommerce.member.util.CustomPasswordEncoder;
import com.microcommerce.member.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles(profiles = "test")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberServiceImpl memberService;

    @Spy
    private CustomPasswordEncoder passwordEncoder;

    @Spy
    private JwtUtil jwtUtil;

    @Mock
    MemberRepository memberRepository;

    SignUpReqDto signUpReq;

    Member newMember;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtUtil, "ACCESS_TOKEN_SECRET_KEY", "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ");
        ReflectionTestUtils.setField(jwtUtil, "ACCESS_TOKEN_EXPIRATION_TIME", 60 * 60 * 1000);

        signUpReq = new SignUpReqDto("test@test.com", "테스트 유저", "password123", "01000000000", MemberType.NORMAL);

        newMember = Member.getInstance("test@test.com", passwordEncoder.encode("password123"), "테스트 유저", "01000000000", MemberType.NORMAL);
        ReflectionTestUtils.setField(newMember, "id", 1L);
    }

    @DisplayName("회원가입 테스트")
    @Test
    void signUp() {
        Mockito.when(memberRepository.existsByEmailAndType(signUpReq.email(), signUpReq.type())).thenReturn(false);
        Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(newMember);

        SignUpResDto res = memberService.signUp(signUpReq);
        assertThat(res.email()).isEqualTo(signUpReq.email());
    }

    @DisplayName("로그인 테스트")
    @Test
    void signIn() {
        SignInReqDto req = new SignInReqDto("test@test.com", "password123", MemberType.NORMAL);

        Mockito.when(memberRepository.findByEmailAndType(Mockito.matches(req.email()), Mockito.eq(req.type()))).thenReturn(Optional.of(newMember));

        SignInResDto res = memberService.signIn(req);
        assertThat(res.accessToken()).isNotNull().isNotEmpty();
    }

    @DisplayName("로그인 테스트[실패: 비밀번호 오류]")
    @Test
    void signInInvalidPwd() {
        SignInReqDto req = new SignInReqDto("test@test.com", "invalid-password123", MemberType.NORMAL);

        Mockito.when(memberRepository.findByEmailAndType(Mockito.matches(req.email()), Mockito.eq(req.type()))).thenReturn(Optional.of(newMember));

        Assertions.assertThrows(MemberException.class, () -> memberService.signIn(req));
    }


}