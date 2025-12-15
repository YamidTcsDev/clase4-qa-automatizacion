# 🏦 Banco Demo - Backend API REST

Backend de aplicación bancaria desarrollado con **Spring Boot 3.2** y **Java 17** para práctica de automatización de pruebas.

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 17 o superior
- Gradle 8+ (opcional, usa el wrapper incluido)

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
