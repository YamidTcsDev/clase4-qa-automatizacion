# 🏦 Banco Demo - Backend API REST

API REST para sistema bancario desarrollada con **Spring Boot 3.4.1** que proporciona funcionalidades de autenticación, consulta de saldos y gestión de préstamos.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Endpoints](#endpoints)
- [Diagramas](#diagramas)
- [Base de Datos](#base-de-datos)
- [Testing](#testing)

## ✨ Características

- 🔐 **Autenticación JWT** - Sistema de login con tokens
- 💰 **Consulta de Saldos** - Información de cuentas bancarias
- 📊 **Cálculo de Préstamos** - Simulador de cuotas con diferentes tasas
- 📝 **Gestión de Solicitudes** - Creación y seguimiento de préstamos
- 📚 **Documentación Swagger** - API docs interactiva
- 🗄️ **Base de Datos H2** - BD en memoria para desarrollo
- ✅ **Validaciones** - Validación de datos con Bean Validation
- 🌐 **CORS Configurado** - Habilitado para frontend en puerto 5500

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas (Layered Architecture):

```
┌─────────────────────────────────────────┐
│          CAPA DE PRESENTACIÓN           │
│    Controllers (REST Endpoints)         │
│  AuthController, CuentaController, etc  │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│          CAPA DE NEGOCIO                │
│        Services (Lógica)                │
│  AuthService, PrestamoService, etc      │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│       CAPA DE PERSISTENCIA              │
│    Repositories (Spring Data JPA)       │
│  UsuarioRepository, CuentaRepository    │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│         BASE DE DATOS H2                │
│    (En memoria - Desarrollo)            │
└─────────────────────────────────────────┘
```

## 🛠️ Tecnologías

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 3.4.1 | Framework principal |
| Spring Data JPA | 3.4.1 | Persistencia de datos |
| Spring Validation | 3.4.1 | Validación de datos |
| H2 Database | Runtime | Base de datos en memoria |
| Lombok | Latest | Reducción de boilerplate |
| Springdoc OpenAPI | 2.7.0 | Documentación Swagger |
| Gradle | 8.5 | Gestión de dependencias |

## 📦 Requisitos Previos

- ☕ **JDK 17 o superior**
- 🔧 **Gradle 8.5+** (o usar el wrapper incluido)
- 🌐 **Puerto 8080** disponible

### Levantar la aplicación

```bash
# En Windows
gradlew.bat bootRun

# En Linux/Mac
./gradlew bootRun
```

La aplicación estará disponible en:
- **API REST:** http://localhost:8080
- **H2 Console:** http://localhost:8080/h2-console

### Configuración H2 Console
- JDBC URL: `jdbc:h2:mem:bancodb`
- Username: `sa`
- Password: *(dejar vacío)*

## 📡 Endpoints Disponibles

### 🔐 Autenticación

#### POST `/api/auth/login`
Autentica un usuario.

**Request:**
```json
{
  "email": "test.qa@banco.com",
  "password": "TestQA2024!"
}
```

**Response:**
```json
{
  "token": "MTp0ZXN0LnFhQGJhbmNvLmNvbTo...",
  "nombre": "QA Tester",
  "userId": 1
}
```

### 💰 Cuentas

#### GET `/api/cuentas/saldo?userId={id}`
Consulta el saldo de la cuenta principal del usuario.

**Response:**
```json
{
  "numeroCuenta": "****7890",
  "saldo": 1500000.00,
  "moneda": "COP",
  "tipoCuenta": "AHORROS"
}
```

### 💳 Préstamos

#### POST `/api/prestamos/calcular`
Calcula la cuota mensual de un préstamo.

**Request:**
```json
{
  "monto": 5000000,
  "plazoMeses": 24
}
```

**Response:**
```json
{
  "cuotaMensual": 230417,
  "tasaInteres": 1.5,
  "totalPagar": 5530008
}
```

#### POST `/api/prestamos/solicitar`
Crea una nueva solicitud de préstamo.

**Request:**
```json
{
  "userId": 2,
  "monto": 5000000,
  "plazoMeses": 24,
  "proposito": "Vehiculo"
}
```

**Response:**
```json
{
  "numeroSolicitud": "SOL-20241214-0002",
  "estado": "EN_REVISION",
  "fechaCreacion": "2024-12-14T15:30:00"
}
```

#### GET `/api/prestamos/solicitudes?userId={id}`
Obtiene todas las solicitudes de un usuario.

**Response:**
```json
[
  {
    "numeroSolicitud": "SOL-20241125-0001",
    "monto": 5000000,
    "plazoMeses": 24,
    "cuotaMensual": 230417,
    "estado": "EN_REVISION",
    "fechaSolicitud": "2024-11-25"
  }
]
```

## 👥 Datos de Prueba

### Usuarios
| Email | Password | Nombre |
|-------|----------|--------|
| test.qa@banco.com | TestQA2024! | QA Tester |
| qa.test@banco.com | CypressTest2024! | Cypress User |

### Cuentas
- **Usuario 1:** Cuenta Ahorros (1234567890) - $1,500,000
- **Usuario 2:** Cuenta Ahorros (5555666677) - $3,000,000

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (en memoria)
- **Lombok**
- **Gradle 8**

## 📁 Estructura del Proyecto

```
backend/
├── src/main/java/com/banco/demo/
│   ├── BancoApplication.java
│   ├── config/
│   │   └── CorsConfig.java
│   ├── controller/
│   │   ├── AuthController.java
│   │   ├── CuentaController.java
│   │   └── PrestamoController.java
│   ├── service/
│   │   ├── AuthService.java
│   │   ├── CuentaService.java
│   │   └── PrestamoService.java
│   ├── model/
│   │   ├── Usuario.java
│   │   ├── Cuenta.java
│   │   └── SolicitudPrestamo.java
│   ├── dto/
│   ├── repository/
│   └── exception/
│       └── GlobalExceptionHandler.java
└── src/main/resources/
    ├── application.properties
    └── data.sql
```

## ✅ Testing

```bash
# Ejecutar tests
gradlew test

# Ver reporte
# build/reports/tests/test/index.html
```

## 📝 Logs

Los logs informativos están habilitados en todos los endpoints:
- ✅ Login exitoso
- 📥 Request recibidos
- ❌ Errores y excepciones

## 🔧 Configuración CORS

CORS habilitado para:
- http://localhost:3000
- http://127.0.0.1:3000

## 🎯 Características

- ✅ Validaciones con `@Valid`
- ✅ Manejo de excepciones con `@ControllerAdvice`
- ✅ Logs informativos en cada operación
- ✅ Base de datos H2 con datos precargados
- ✅ API REST completamente funcional
- ✅ Sin autenticación compleja (para facilitar testing)

---

**Desarrollado para práctica de automatización QA** 🚀
