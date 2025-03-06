package com.ervingorospe.grab_api_gateway.config;

import com.ervingorospe.grab_api_gateway.config.security.JwtFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, JwtFilter jwtFilter) {
       /*
        *adding header
        Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")
                .filters(f -> f
                        .addRequestHeader("MyHeader", "MyURI")
                        .addRequestParameter("Param", "MyValue")
                )
                .uri("http://httpbin.org:80");
        */
        return builder.routes()
                .route(r -> r
                        .path("/auth/**")
                        .uri("http://localhost:9000")
                )
                .route(r -> r
                        .path("/test/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:9000")
                )
                .route(r -> r
                        .path("/api/user/**")
                        .uri("http://localhost:8000")
                )
                .build();
    }
}
