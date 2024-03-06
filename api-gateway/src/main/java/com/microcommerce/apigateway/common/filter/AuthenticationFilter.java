package com.microcommerce.apigateway.common.filter;

import com.microcommerce.apigateway.domain.dto.res.UserData;
import com.microcommerce.apigateway.infrastructure.feign.MemberClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final MemberClient memberClient;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) ->{
            final ServerHttpRequest request = exchange.getRequest();
            final String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            final UserData auth = memberClient.getProfile(token);
            if (auth.userId() != null) {
                final ServerHttpRequest newReq = exchange.getRequest().mutate()
                        .header("x-user-id", auth.userId().toString())
                        .build();
                return chain.filter(exchange.mutate().request(newReq).build());
            }

            final ServerHttpResponse httpResponse = exchange.getResponse();
            httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return httpResponse.setComplete();
        };

    }

    public static class Config{
        //Put the configuration properties
    }
}
