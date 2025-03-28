package sakhno.springframework.mssc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceRoutesConfig {

    /**
     * Метод создает объект, который маршрутизирует запросы
     * @param builder - строить для маршрутизации
     * @return - маршрутизатор
     */
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
                        .filters(f -> f.circuitBreaker(c -> c
                                .setName("inventoryCB")
                                .setFallbackUri("forward:/inventory-failover")
                                .setRouteId("inventory-failover")))
                        .uri("lb://MSSC-BEER-INVENTORY-SERVICE"))
                .route("inventory-failover", r -> r
                        .path("/inventory-failover/**")
                        .uri("lb://MSSC-INVENTORY-FAILOVER"))
                .build();
    }
}
