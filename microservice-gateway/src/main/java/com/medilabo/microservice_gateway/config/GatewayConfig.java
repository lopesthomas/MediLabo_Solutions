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
				.path("/api/patients/**")
				.uri("http://microservice-patient:8081"))
			.route("note_route", r -> r
				.path("/api/notes/**")
				.uri("http://microservice-note:8083"))
			.route("rapport_route", r -> r
				.path("/api/report/**")
				.uri("http://microservice-rapport:8084"))
			.route("frontend_route", r -> r
				.path("/**")
				.uri("http://frontend:8082"))
			.build();
    }

}