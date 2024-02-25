package com.microcommerce.apigateway.infrastructure;

import com.microcommerce.apigateway.domain.dto.res.AuthResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="AuthOpenFeign", url="http://MICRO-COMMERCE-MEMBER")
public interface AuthOpenFeign {

    @PostMapping("/public-api/v1/member/auth")
    AuthResDto authenticateJwt(@RequestBody String jwt);

}
