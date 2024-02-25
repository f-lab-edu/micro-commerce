package com.microcommerce.member.application;

import com.microcommerce.member.exception.MemberException;
import com.microcommerce.member.exception.MemberExceptionCode;
import com.microcommerce.member.domain.entity.Member;
import com.microcommerce.member.infrastructure.repository.MemberRepository;
import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
import com.microcommerce.member.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public SignUpResDto signUp(final SignUpReqDto body) {
        if (memberRepository.existsByEmailAndType(body.email(), body.type())) {
            throw new MemberException(MemberExceptionCode.EXISTS_USER);
        }

        final String hashedPassword = passwordEncoder.encode(body.password());
        final Member newMember = memberRepository.save(
                Member.getInstance(body.email(), hashedPassword, body.name(), body.phoneNumber(), body.type())
        );

        return SignUpResDto.getInstance(newMember.getEmail());
    }

    @Override
    public SignInResDto signIn(final SignInReqDto body) {
        final Member member = memberRepository.findByEmailAndType(body.email(), body.type())
                .filter(m -> passwordEncoder.matches(body.password(), m.getPassword()))
                .orElseThrow(() -> new MemberException(MemberExceptionCode.INVALID_USER_INFO));

        final String accessToken = jwtUtil.createToken(member.getId().toString(), Map.of("email", member.getEmail()));
        return SignInResDto.getInstance(accessToken);
    }

}
