package com.sport.teams;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF globalmente para prueba
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers("/h2-console/**").permitAll()
	            .anyRequest().permitAll()
	        )
	        .headers(headers -> headers
	            .frameOptions(frameOptions -> frameOptions.sameOrigin())
	        );

	    return http.build();
	}
}

