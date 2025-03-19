package sakhno.springframework.mssc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleConfig {
    /**
     * Настройка маршрутизации
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("google", r -> r
                        .path("/googlesearchone/**")
                        .filters(f -> f.rewritePath("/googlesearchone(?<segment>.*)", "/${segment}"))
                        .uri("https://www.google.com"))
                .build();
    }
}
