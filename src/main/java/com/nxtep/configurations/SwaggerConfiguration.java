package com.nxtep.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    private static final String AUTH_SCHEME = "Authorization";
    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.schemaRequirement(AUTH_SCHEME, createSwaggerSecurityScheme());
        openAPI.setInfo(createSwaggerInfo());
        return openAPI;
    }
    private SecurityScheme createSwaggerSecurityScheme() {
        return new SecurityScheme()
            .name(AUTH_SCHEME)
            .type(SecurityScheme.Type.HTTP)
            .scheme("Basic");
    }
    private Contact createSwaggerContact() {
        return new Contact()
            .name("Angel Eduardo Hincho Jove")
            .email("ahincho@unsa.edu.pe")
            .url("https://github.com/ahincho");
    }
    private Info createSwaggerInfo() {
        return new Info()
            .title("Nxtep - Baby Face Web Application")
            .version("1.0")
            .description("Baby Face Web Application")
            .contact(createSwaggerContact());
    }
}
