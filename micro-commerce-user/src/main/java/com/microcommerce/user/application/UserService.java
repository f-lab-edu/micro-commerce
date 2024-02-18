package com.microcommerce.user.application;

import com.microcommerce.user.dto.req.SignInReqDto;
import com.microcommerce.user.dto.req.SignUpReqDto;
import com.microcommerce.user.dto.res.SignInResDto;
import com.microcommerce.user.dto.res.SignUpResDto;

public interface UserService {

    SignUpResDto signUp(SignUpReqDto body);

    SignInResDto signIn(SignInReqDto body);

}
