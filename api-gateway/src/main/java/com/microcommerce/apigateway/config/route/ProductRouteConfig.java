package com.microcommerce.apigateway.config.route;

import com.microcommerce.apigateway.common.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRouteConfig {

    @Bean
    public RouteLocator productRoutes(final RouteLocatorBuilder builder, final AuthenticationFilter authenticationFilter) {
        return builder.routes()
                .route(r -> r.path("/api/v*/products/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MICRO-COMMERCE-PRODUCT")
                )
                .route(r -> r.path("/public-api/v*/products/**")
                        .uri("lb://MICRO-COMMERCE-PRODUCT")
                ).build();
    }

}
