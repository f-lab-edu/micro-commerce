package com.microcommerce.member.presentation;

import com.microcommerce.member.application.MemberService;
import com.microcommerce.member.domain.dto.ApiResult;
import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResult<SignUpResDto>> signUp(@RequestBody final SignUpReqDto body) {
        // TODO: created API 경우 적절한 응답값
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(memberService.signUp(body)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResult<SignInResDto>> signIn(@RequestBody final SignInReqDto body) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.success(memberService.signIn(body)));
    }

}
