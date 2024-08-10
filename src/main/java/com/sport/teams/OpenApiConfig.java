package com.sport.teams;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	   @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info().title("TEAMS"))
	                .addSecurityItem(new SecurityRequirement().addList("bearerToken"))
	                .components(new io.swagger.v3.oas.models.Components()
	                        .addSecuritySchemes("bearerToken", new SecurityScheme()
	                                .type(SecurityScheme.Type.HTTP)
	                                .scheme("bearer")
	                                .bearerFormat("JWT")));
	    }
	 @Bean
	    public GroupedOpenApi publicApi() {
	        return GroupedOpenApi.builder()
	                .group("public")
	                .pathsToMatch("/**")
	                .build();
	    }
}

