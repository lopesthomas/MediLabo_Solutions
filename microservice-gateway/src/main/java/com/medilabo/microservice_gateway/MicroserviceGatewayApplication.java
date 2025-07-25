package com.medilabo.microservice_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class, args);
	}

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
