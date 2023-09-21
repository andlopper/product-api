package com.andlopper.productapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIDefinition() {

        return new OpenAPI()
                .info(new Info().title("Gerenciamento de produtos")
                        .contact(new Contact().name("André Pereira").email("andrelopespereiraa@gmail.com"))
                        .description("Aplicação desenvolvida para gerenciamento de produtos.")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation().description("GitHub")
                        .url("https://github.com/andlopper/products-api"));
    }
}
