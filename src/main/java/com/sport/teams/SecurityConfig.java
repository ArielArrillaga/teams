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
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/h2-console/**").permitAll() // Permitir el acceso a la consola de H2
                .anyRequest().permitAll() // Permitir todas las demás solicitudes sin autenticación
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) // Deshabilitar la protección CSRF solo para H2
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permitir que H2 se cargue dentro de un frame
            );

        return http.build();
    }
}

