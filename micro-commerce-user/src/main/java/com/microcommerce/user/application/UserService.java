package com.microcommerce.user.application;

import com.microcommerce.user.dto.req.SignupReqDto;
import com.microcommerce.user.dto.res.SignupResDto;

public interface UserService {

    SignupResDto signup(SignupReqDto body);

}
