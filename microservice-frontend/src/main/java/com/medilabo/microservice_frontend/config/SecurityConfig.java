package com.medilabo.microservice_frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.Customizer;

@Profile("!test")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final ClientRegistrationRepository clientRegistrationRepository;

    // Spring Boot will inject it automatically based on your
    // application.yml/properties
    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(
                clientRegistrationRepository);

        // This will send the user to Keycloak’s end_session_endpoint
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:8082/listpatients");

        return oidcLogoutSuccessHandler;
    }
}
