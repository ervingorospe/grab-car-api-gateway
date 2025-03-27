package com.ervingorospe.grab_api_gateway.config;

import com.ervingorospe.grab_api_gateway.config.security.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.List;

@Configuration
public class ApiGatewayConfig {
    @Value("${authServiceUrl}")
    private String authServiceUrl;

    @Value("${userServiceUrl}")
    private String userServiceUrl;

    @Value("${tokenServiceUrl}")
    private String tokenServiceUrl;

    @Value("${bookingServiceUrl}")
    private String bookingServiceUrl;

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
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("authCircuitBreaker")
                                .setFallbackUri("forward:/fallback/auth")
                        ))
                        .uri(authServiceUrl)
                )
                .route(r -> r
                        .path("/token/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("authCircuitBreaker")
                                .setFallbackUri("forward:/fallback/token")
                        ))
                        .uri(tokenServiceUrl)
                )
                .route(r -> r
                        .path("/test/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri(authServiceUrl)
                )
                .route(r -> r
                        .path("/api/user/**")
                        .filters(f -> f.filter(jwtFilter)
                                .circuitBreaker(config -> config
                                        .setName("userCircuitBreaker")
                                        .setFallbackUri("forward:/fallback:user")
                                )
                        )
                        .uri(userServiceUrl)
                )
                .route(r -> r
                        .path("/api/booking/**")
                        .filters(f -> f.filter(jwtFilter)
                                .circuitBreaker(config -> config
                                        .setName("bookingCircuitBreaker")
                                        .setFallbackUri("forward:/fallback:booking")
                                )
                        )
                        .uri(bookingServiceUrl)
                )
                .build();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
