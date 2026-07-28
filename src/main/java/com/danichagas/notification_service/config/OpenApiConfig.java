package com.danichagas.notification_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OmniNotify API - Sistema de Notificações")
                        .version("v1.0.0")
                        .description("API robusta para envio de notificações assíncronas utilizando RabbitMQ, Redis (Rate Limiter) e Resiliência.")
                        .contact(new Contact()
                                .name("Daniel Chagas")
                                .url("https://github.com/danichagas")
                                .email("chagasdaniel788@gmail.com")));
    }
}
