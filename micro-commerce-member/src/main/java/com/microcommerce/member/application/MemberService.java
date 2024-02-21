package com.microcommerce.member.application;

import com.microcommerce.member.domain.dto.req.SignInReqDto;
import com.microcommerce.member.domain.dto.req.SignUpReqDto;
import com.microcommerce.member.domain.dto.res.SignInResDto;
import com.microcommerce.member.domain.dto.res.SignUpResDto;

public interface MemberService {

    SignUpResDto signUp(SignUpReqDto body);

    SignInResDto signIn(SignInReqDto body);

}
