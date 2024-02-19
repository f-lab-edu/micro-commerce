package com.microcommerce.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v*/members/**") //라우터 등록
                        .uri("lb://MICRO-COMMERCE-MEMBER")
                )
                .route(r -> r.path("/api/v*/products/**") //라우터 등록
                        .uri("lb://MICRO-COMMERCE-PRODUCT")
                )
                .route(r -> r.path("/api/v*/orders/**") //라우터 등록
                        .uri("lb://MICRO-COMMERCE-ORDER")
                )
                .build();
    }

}
