package com.medilabo.microservice_frontend.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FeignConfig {

    @Bean
    @Profile("!test")
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2FeignRequestInterceptor interceptor) {
        return interceptor;
    }

    @Bean
    @Profile("test")
    public RequestInterceptor testFeignRequestInterceptor() {
        return requestTemplate -> { /* no-op */ };
    }
}