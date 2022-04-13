package ru.fedusiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class GatewayService {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("students-service",
                        r -> r.path("/students-service/**")
                                .filters(f -> f.stripPrefix(1))
                                .uri("lb://MAIN-SERVICE"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayService.class, args);
    }

}
