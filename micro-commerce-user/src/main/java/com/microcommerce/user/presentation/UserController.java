package com.microcommerce.user.presentation;

import com.microcommerce.user.application.UserService;
import com.microcommerce.user.dto.ApiResult;
import com.microcommerce.user.dto.req.SignupReqDto;
import com.microcommerce.user.dto.res.SignupResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResult<SignupResDto>> signup(@RequestBody final SignupReqDto body) {
        // TODO: created API 경우 적절한 응답값
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.success(userService.signup(body)));
    }

}
