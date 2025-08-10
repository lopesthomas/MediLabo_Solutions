package com.medilabo.microservice_rapport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceRapportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRapportApplication.class, args);
	}

}
