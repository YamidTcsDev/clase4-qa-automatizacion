package com.banco.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de Swagger/OpenAPI para documentación de la API
 * Accesible en: http://localhost:8080/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bancoOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Servidor local de desarrollo");

        Contact contact = new Contact();
        contact.setEmail("soporte@bancodemo.com");
        contact.setName("Equipo de Desarrollo Banco Demo");
        contact.setUrl("https://bancodemo.com");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API REST - Banco Demo")
                .version("1.0.0")
                .contact(contact)
                .description("API REST para sistema bancario con funcionalidades de autenticación, " +
                        "consulta de saldos y gestión de préstamos. Desarrollado con Spring Boot 3.4.1.")
                .termsOfService("https://bancodemo.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer));
    }
}
