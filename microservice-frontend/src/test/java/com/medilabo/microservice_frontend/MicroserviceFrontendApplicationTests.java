package com.medilabo.microservice_frontend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.medilabo.microservice_frontend.config.TestSecurityConfig;

@SpringBootTest(properties = {"spring.profiles.active=test"})
@Import(TestSecurityConfig.class)
class MicroserviceFrontendApplicationTests {

	@Test
	void contextLoads() {
	}

}
