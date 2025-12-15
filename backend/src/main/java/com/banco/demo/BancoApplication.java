package com.banco.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación bancaria demo
 * Backend REST API para práctica de automatización de pruebas
 * 
 * @author Banco Demo Team
 * @version 1.0.0
 */
@SpringBootApplication
public class BancoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoApplication.class, args);
        System.out.println("==============================================");
        System.out.println("🏦 Banco Demo Backend - Iniciado exitosamente");
        System.out.println("==============================================");
        System.out.println("📍 API REST: http://localhost:8080");
        System.out.println("📍 H2 Console: http://localhost:8080/h2-console");
        System.out.println("==============================================");
    }
}
