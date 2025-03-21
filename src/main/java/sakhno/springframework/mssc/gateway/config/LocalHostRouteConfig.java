package sakhno.springframework.mssc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator localHostRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("beer-service", r -> r
                        .path("/api/v1/beer/**")
                        .uri("http://localhost:8080"))
                .route("beer-order-service", r -> r
                        .path("/api/v1/customer/**")
                        .uri("http://localhost:8081"))
                .route("beer-inventory-service", r -> r
                        .path("/api/v1/inventory/beer/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
