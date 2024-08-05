package com.springboot.jpa.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {

        Contact contact = new Contact();
        contact.name("jh4dev").email("jh4dev.gmail.com");
        return new Info()
                .title("JPA Simple Example Swagger")
                .description("JPA Simple Example")
                .contact(contact)
                .version("ver. 1.0.1");
    }
}
