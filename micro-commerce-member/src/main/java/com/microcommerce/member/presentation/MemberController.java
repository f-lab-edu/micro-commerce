package com.microcommerce.member.presentation;

import com.microcommerce.member.application.MemberService;
import com.microcommerce.member.domain.dto.ApiResult;
import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
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
    public ResponseEntity<ApiResult<String>> getProfile(@RequestHeader HttpHeaders header, @PathVariable("userId") final int userId) {
        String tokenUid = header.getFirst("x-user-id");
        if (!Integer.toString(userId).equals(tokenUid)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResult.fail("Forbidden"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.success("성공"));
    }

}
