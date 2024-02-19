package com.microcommerce.member.application;

import com.microcommerce.member.dto.req.SignInReqDto;
import com.microcommerce.member.dto.req.SignUpReqDto;
import com.microcommerce.member.dto.res.SignInResDto;
import com.microcommerce.member.dto.res.SignUpResDto;

public interface MemberService {

    SignUpResDto signUp(SignUpReqDto body);

    SignInResDto signIn(SignInReqDto body);

}
