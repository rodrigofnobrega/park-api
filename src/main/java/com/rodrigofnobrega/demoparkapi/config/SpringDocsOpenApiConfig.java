package com.rodrigofnobrega.demoparkapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("security", securityScheme()))
                .info(
                        new Info()
                                .title("Rest Api - Spring Park")
                                .description("Api para gestão de estacionamento de veículos")
                                .version("v1")
                                .license(new License().name("MIT").url("https://mit-license.org/"))
                                .contact(new Contact().name("Rodrigo Nobrega").url("https://github.com/rodrigofnobrega/"))
                );
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .description("Insira um bearer token válido para proseeguir")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("security");
    }
}
