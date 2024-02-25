package com.microcommerce.apigateway.common.filter;

import com.microcommerce.apigateway.domain.dto.res.AuthResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter implements GatewayFilter {

    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        return webClientBuilder.build()
                .post()
                .uri("http://MICRO-COMMERCE-MEMBER/api/v1/auth")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(AuthResDto.class)
                .flatMap(response -> {
                    if (response.success()) {
                        final ServerHttpRequest newReq = exchange.getRequest().mutate()
                                .header("x-user-id", response.data().userId().toString())
                                .build();
                        return chain.filter(exchange.mutate().request(newReq).build()); // 요청 진행
                    } else {
                        ServerHttpResponse httpResponse = exchange.getResponse();
                        httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return httpResponse.setComplete();
                    }
                }).onErrorResume(e -> {
                    log.error(e.getMessage());
                    ServerHttpResponse httpResponse = exchange.getResponse();
                    httpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                    return httpResponse.setComplete();
                });
    }

//    @Override
//    public int getOrder() {
//        return -1;
//    }

}
