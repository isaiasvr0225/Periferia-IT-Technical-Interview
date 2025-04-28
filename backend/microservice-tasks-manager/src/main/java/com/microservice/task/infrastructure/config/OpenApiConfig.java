package com.microservice.task.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * @package : com.microservice.task.infrastructure.config
 * @name : OpenApiConfig.java
 * @date : 2025-27-04
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@OpenAPIDefinition(
        info = @Info(
                title = "APIs Periferia IT",
                description = "APIs para el manejo de tareas",
                termsOfService = "Licencia de uso de software estándar para Periferia IT",
                version = "1.0.0",
                contact = @Contact(
                        name = "Isaias Villarreal",
                        url = "wa.me/573116112594",
                        email = "isaiasvillarreal0225@mail.com"
                ),
                license = @License(
                        name = "Standard Software Use License for Periferia IT",
                        url = "Url here"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "url"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "Url here"
                )
        }
        /*security = @SecurityRequirement(
                name = "Security Token"
        ) */
)
public @Configuration class OpenApiConfig {}
