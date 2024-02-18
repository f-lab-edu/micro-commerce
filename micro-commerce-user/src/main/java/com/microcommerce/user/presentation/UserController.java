package com.microcommerce.user.presentation;

import com.microcommerce.user.application.UserService;
import com.microcommerce.user.dto.ApiResult;
import com.microcommerce.user.dto.req.SignInReqDto;
import com.microcommerce.user.dto.req.SignUpReqDto;
import com.microcommerce.user.dto.res.SignInResDto;
import com.microcommerce.user.dto.res.SignUpResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResult<SignUpResDto>> signUp(@RequestBody final SignUpReqDto body) {
        // TODO: created API 경우 적절한 응답값
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(userService.signUp(body)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResult<SignInResDto>> signIn(@RequestBody final SignInReqDto body) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.success(userService.signIn(body)));
    }

}
