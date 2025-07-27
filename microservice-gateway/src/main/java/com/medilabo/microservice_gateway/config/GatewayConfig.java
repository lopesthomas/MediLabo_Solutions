package com.medilabo.microservice_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
			.route("patient_route", r -> r
				.path("/patients/**")
				.uri("http://localhost:8081"))
			.route("frontend_route", r -> r
				.path("/frontend/**")
				.uri("http://localhost:8082"))
			.build();
    }

}