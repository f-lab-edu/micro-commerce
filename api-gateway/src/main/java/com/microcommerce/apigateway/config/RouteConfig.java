package com.microcommerce.apigateway.config;

import com.microcommerce.apigateway.common.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

//    @Autowired
//    private GlobalAuthFilter globalAuthFilter;

//    @Bean
//    public GlobalFilter setGlobalFilter() {
//        return  globalAuthFilter;
//
//    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
        return builder.routes()
                .route(r -> r.path("/api/v*/members/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MICRO-COMMERCE-MEMBER")
                )
                .route(r -> r.path("/public-api/v*/members/**")
                        .uri("lb://MICRO-COMMERCE-MEMBER")
                )
                .route(r -> r.path("/api/v*/products/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://MICRO-COMMERCE-PRODUCT")
                )
                .route(r -> r.path( "/public-api/v*/products/**")
                        .uri("lb://MICRO-COMMERCE-PRODUCT")
                )
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
