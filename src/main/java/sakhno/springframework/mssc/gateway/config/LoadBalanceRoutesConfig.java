package sakhno.springframework.mssc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceRoutesConfig {
    @Bean
    public RouteLocator loadBalanceRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("beer-service", r -> r
                        .path("/api/v1/beer/**")
                        .uri("lb://MSSC-BEER-SERVICE"))
                .route("beer-order-service", r -> r
                        .path("/api/v1/customer/**")
                        .uri("lb://MSSC-BEER-ORDER-SERVICE"))
                .route("beer-inventory-service", r -> r
                        .path("/api/v1/inventory/beer/**")
                        .uri("lb://MSSC-BEER-INVENTORY-SERVICE"))
                .build();
    }
}
