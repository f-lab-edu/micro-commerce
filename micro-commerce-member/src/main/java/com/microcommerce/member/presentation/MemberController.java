package com.microcommerce.member.presentation;

import com.microcommerce.member.application.MemberService;
import com.microcommerce.member.domain.dto.ApiResult;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/public-api/v1/members/sign-up")
    public ResponseEntity<ApiResult<SignUpResDto>> signUp(@RequestBody final SignUpReqDto body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(memberService.signUp(body)));
    }

    @PostMapping("/public-api/v1/members/sign-in")
    public ResponseEntity<ApiResult<SignInResDto>> signIn(@RequestBody final SignInReqDto body) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.success(memberService.signIn(body)));
    }

    @GetMapping("/api/v1/members/{userId}/profile")
    public ResponseEntity<ApiResult<ProfileResDto>> getProfile(@RequestHeader HttpHeaders header, @PathVariable("userId") final long userId) {
        String tokenUid = header.getFirst("x-user-id");
        if (!Long.toString(userId).equals(tokenUid)) {
            throw new MemberException(MemberExceptionCode.FORBIDDEN);
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.success(memberService.getProfile(userId)));
    }

}
