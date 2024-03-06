package com.microcommerce.apigateway.config.route;

import com.microcommerce.apigateway.common.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberRouteConfig {

    @Bean
    public RouteLocator memberRoutes(final RouteLocatorBuilder builder, final AuthenticationFilter authenticationFilter) {
        return builder.routes()
                .route(r -> r.path("/api/v*/members/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://MICRO-COMMERCE-MEMBER")
                )
                .route(r -> r.path("/public-api/v*/members/**")
                        .uri("lb://MICRO-COMMERCE-MEMBER")
                )
                .build();
    }

}
