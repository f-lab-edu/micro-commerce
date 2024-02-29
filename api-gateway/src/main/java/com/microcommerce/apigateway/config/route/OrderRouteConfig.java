package com.microcommerce.apigateway.config.route;

import com.microcommerce.apigateway.common.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRouteConfig {

    @Bean
    public RouteLocator orderRoutes(final RouteLocatorBuilder builder, final AuthenticationFilter authenticationFilter) {
        return builder.routes()
                .route(r -> r.path("/api/v*/orders/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MICRO-COMMERCE-ORDER")
                )
                .route(r -> r.path("/public-api/v*/orders/**")
                        .uri("lb://MICRO-COMMERCE-ORDER")
                )
                .build();
    }

}
