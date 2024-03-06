package com.microcommerce.member.presentation;

import com.microcommerce.member.application.MemberService;
import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.ProfileResDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
import com.microcommerce.member.exception.MemberException;
import com.microcommerce.member.exception.MemberExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/public-api/v1/members/sign-up")
    public SignUpResDto signUp(@RequestBody final SignUpReqDto body) {
        return memberService.signUp(body);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/public-api/v1/members/sign-in")
    public SignInResDto signIn(@RequestBody final SignInReqDto body) {
        return memberService.signIn(body);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/members/{userId}/profile")
    public ProfileResDto getProfile(@RequestHeader final HttpHeaders header, @PathVariable("userId") final long userId) {
        final String tokenUid = header.getFirst("x-user-id");
        if (!Long.toString(userId).equals(tokenUid)) {
            throw new MemberException(MemberExceptionCode.FORBIDDEN);
        }
        return memberService.getProfile(userId);
    }

}
