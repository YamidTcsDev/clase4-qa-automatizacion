# 🏦 Aplicación Bancaria Demo - Recurso para Clase 4 (Automatización)

## 📋 Objetivo
Crear una aplicación web bancaria simple que sirva como recurso de práctica para automatización con Selenium (Java/Gradle) y Cypress (JavaScript). Esta app debe tener todos los elementos necesarios para los **Ejemplo 1** y **Ejemplo 2** de la Clase 4.

---

## 🎯 Características de la Aplicación

### **Funcionalidades Requeridas:**
1. ✅ **Login funcional** con validación de credenciales
2. ✅ **Dashboard** con información del usuario
3. ✅ **Consulta de saldo** de cuenta bancaria
4. ✅ **Formulario de solicitud de préstamo** con validaciones
5. ✅ **Cálculo de cuota mensual** basado en monto y plazo
6. ✅ **Confirmación y listado** de solicitudes

### **Requisitos Técnicos:**
- Backend: **Spring Boot** (Java 17+) con API REST
- Frontend: **HTML + JavaScript Vanilla** (sin frameworks complejos)
- Base de datos: **H2 en memoria** (para facilidad de setup)
- Selectores: Todos los elementos con `data-testid` para automatización
- CORS habilitado para testing local

---

## 🏗️ Estructura del Proyecto

```
banco-demo-app/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/banco/demo/
│   │   │   │   ├── BancoApplication.java
│   │   │   │   ├── config/
│   │   │   │   │   └── CorsConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── CuentaController.java
│   │   │   │   │   └── PrestamoController.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── AuthService.java
│   │   │   │   │   ├── CuentaService.java
│   │   │   │   │   └── PrestamoService.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── Usuario.java
│   │   │   │   │   ├── Cuenta.java
│   │   │   │   │   └── Solicitud.java
│   │   │   │   └── dto/
│   │   │   │       ├── LoginRequest.java
│   │   │   │       ├── LoginResponse.java
│   │   │   │       ├── SaldoResponse.java
│   │   │   │       └── PrestamoRequest.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── data.sql (datos iniciales)
│   │   └── test/ (tests unitarios)
│   ├── build.gradle
│   └── settings.gradle
├── frontend/
│   ├── index.html (landing)
│   ├── login.html
│   ├── dashboard.html
│   ├── consulta-saldo.html
│   ├── solicitud-prestamo.html
│   ├── mis-solicitudes.html
│   ├── css/
│   │   └── styles.css
│   └── js/
│       ├── api.js (funciones API)
│       ├── auth.js (manejo autenticación)
│       └── utils.js
└── automation-tests/
    ├── selenium-java/
    │   ├── src/test/java/
    │   ├── build.gradle
    │   └── README.md
    └── cypress/
        ├── cypress/e2e/
        ├── cypress.config.js
        ├── package.json
        └── README.md
```

---

## 🔧 Dependencias y Configuración

### **Backend - Spring Boot (build.gradle)**

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.0'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.banco'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // H2 Database
    runtimeOnly 'com.h2database:h2'
    
    // Lombok (opcional, para reducir boilerplate)
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    
    // Testing
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

### **Frontend - package.json (para servir estático)**

```json
{
  "name": "banco-demo-frontend",
  "version": "1.0.0",
  "description": "Frontend bancario para testing",
  "scripts": {
    "start": "npx http-server . -p 3000 -c-1",
    "dev": "npx live-server --port=3000 --no-browser"
  },
  "devDependencies": {
    "http-server": "^14.1.1",
    "live-server": "^1.2.2"
  }
}
```

### **Automation Tests - Selenium Java (build.gradle)**

```gradle
plugins {
    id 'java'
}

group = 'com.banco.tests'
version = '1.0.0'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    // Selenium WebDriver
    testImplementation 'org.seleniumhq.selenium:selenium-java:4.16.1'
    
    // WebDriverManager (gestiona drivers automáticamente)
    testImplementation 'io.github.bonigarcia:webdrivermanager:5.6.3'
    
    // JUnit 5
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.1'
    
    // AssertJ (assertions fluidas)
    testImplementation 'org.assertj:assertj-core:3.24.2'
    
    // Logging
    testImplementation 'org.slf4j:slf4j-simple:2.0.9'
}

tasks.named('test') {
    useJUnitPlatform()
    testLogging {
        events "passed", "skipped", "failed"
        showStandardStreams = true
    }
}
```

### **Automation Tests - Cypress (package.json)**

```json
{
  "name": "banco-demo-cypress-tests",
  "version": "1.0.0",
  "description": "Tests E2E con Cypress",
  "scripts": {
    "cypress:open": "cypress open",
    "cypress:run": "cypress run",
    "test": "cypress run --spec 'cypress/e2e/**/*.cy.js'",
    "test:chrome": "cypress run --browser chrome",
    "test:headed": "cypress run --headed"
  },
  "devDependencies": {
    "cypress": "^13.6.2"
  }
}
```

---

## 📝 Datos de Prueba (data.sql)

```sql
-- Usuarios de prueba
INSERT INTO usuarios (id, email, password, nombre, fecha_registro) VALUES
(1, 'test.qa@banco.com', 'TestQA2024!', 'QA Tester', '2023-01-15'),
(2, 'qa.test@banco.com', 'CypressTest2024!', 'Cypress User', '2023-02-20');

-- Cuentas bancarias
INSERT INTO cuentas (id, usuario_id, numero_cuenta, tipo, saldo, fecha_apertura) VALUES
(1, 1, '1234567890', 'AHORROS', 1500000.00, '2023-01-20'),
(2, 1, '0987654321', 'CORRIENTE', 500000.00, '2023-03-10'),
(3, 2, '5555666677', 'AHORROS', 3000000.00, '2023-02-25');

-- Solicitudes de préstamo (para testing de listado)
INSERT INTO solicitudes_prestamo (id, usuario_id, monto, plazo_meses, proposito, cuota_mensual, estado, fecha_solicitud) VALUES
(1, 2, 5000000.00, 24, 'Vehiculo', 230417.00, 'EN_REVISION', '2024-11-15');
```

---

## 🎨 Ejemplo 1: Login + Consulta Saldo

### **Página: login.html**

**Elementos necesarios con data-testid:**
```html
<form id="login-form">
  <input 
    type="email" 
    id="email" 
    data-testid="login-email"
    placeholder="Email"
    required>
  
  <input 
    type="password" 
    id="password" 
    data-testid="login-password"
    placeholder="Contraseña"
    required>
  
  <button 
    type="submit" 
    id="btn-login" 
    data-testid="login-submit">
    Iniciar Sesión
  </button>
  
  <div 
    class="error-message" 
    data-testid="login-error" 
    style="display:none;">
  </div>
</form>
```

### **Página: dashboard.html**

**Elementos necesarios:**
```html
<div class="welcome-message" data-testid="welcome-msg">
  Bienvenido <span id="user-name">QA Tester</span>
</div>

<nav>
  <a href="consulta-saldo.html" data-testid="nav-cuentas">Mis Cuentas</a>
  <a href="solicitud-prestamo.html" data-testid="nav-prestamos">Préstamos</a>
</nav>
```

### **Página: consulta-saldo.html**

**Elementos necesarios:**
```html
<div class="cuenta-info" data-testid="cuenta-card">
  <div class="cuenta-numero" data-testid="cuenta-numero">
    **** **** 1234
  </div>
  
  <div class="saldo-container">
    <span class="saldo-label">Saldo disponible:</span>
    <span class="saldo-amount" data-testid="saldo-amount">
      $1,500,000 COP
    </span>
  </div>
  
  <div class="tipo-cuenta" data-testid="tipo-cuenta">
    Ahorros
  </div>
</div>
```

### **API Endpoints requeridos:**

```
POST /api/auth/login
Request: { "email": "test.qa@banco.com", "password": "TestQA2024!" }
Response: { 
  "token": "eyJhbGc...", 
  "nombre": "QA Tester",
  "userId": 1
}

GET /api/cuentas/saldo?userId=1
Response: {
  "numeroCuenta": "****1234",
  "saldo": 1500000.00,
  "moneda": "COP",
  "tipoCuenta": "AHORROS"
}
```

---

## 🚀 Ejemplo 2: Solicitud de Préstamo

### **Página: solicitud-prestamo.html**

**Formulario completo:**
```html
<form id="prestamo-form">
  <h2>Nueva Solicitud de Préstamo</h2>
  
  <!-- Monto -->
  <div class="form-group">
    <label for="monto">Monto del préstamo</label>
    <input 
      type="number" 
      id="monto" 
      data-testid="input-monto"
      min="1000000"
      max="50000000"
      step="100000"
      required>
    <span class="error" data-testid="error-monto"></span>
  </div>
  
  <!-- Plazo -->
  <div class="form-group">
    <label for="plazo">Plazo en meses</label>
    <select id="plazo" data-testid="select-plazo" required>
      <option value="">Seleccione...</option>
      <option value="12">12 meses</option>
      <option value="24">24 meses</option>
      <option value="36">36 meses</option>
      <option value="48">48 meses</option>
    </select>
    <span class="error" data-testid="error-plazo"></span>
  </div>
  
  <!-- Propósito -->
  <div class="form-group">
    <label>Propósito del préstamo</label>
    <div class="radio-group">
      <label>
        <input 
          type="radio" 
          name="proposito" 
          value="Vehiculo"
          data-testid="radio-proposito-vehiculo">
        Vehículo
      </label>
      <label>
        <input 
          type="radio" 
          name="proposito" 
          value="Vivienda"
          data-testid="radio-proposito-vivienda">
        Vivienda
      </label>
      <label>
        <input 
          type="radio" 
          name="proposito" 
          value="Educacion"
          data-testid="radio-proposito-educacion">
        Educación
      </label>
    </div>
    <span class="error" data-testid="error-proposito"></span>
  </div>
  
  <!-- Botón calcular -->
  <button 
    type="button" 
    id="btn-calcular" 
    data-testid="btn-calcular">
    Calcular Cuota
  </button>
  
  <!-- Resultado del cálculo -->
  <div 
    id="resultado-calculo" 
    data-testid="resultado-calculo" 
    style="display:none;">
    <h3>Cuota Mensual</h3>
    <div class="cuota-mensual" data-testid="cuota-mensual">
      $230,417
    </div>
    <div class="detalle">
      <p>Tasa de interés: <span data-testid="tasa-interes">1.5%</span></p>
      <p>Total a pagar: <span data-testid="total-pagar">$5,530,008</span></p>
    </div>
  </div>
  
  <!-- Términos y condiciones -->
  <div class="form-group">
    <label>
      <input 
        type="checkbox" 
        id="terminos" 
        data-testid="checkbox-terminos">
      Acepto términos y condiciones
    </label>
  </div>
  
  <!-- Spinner (mientras procesa) -->
  <div class="spinner" data-testid="spinner" style="display:none;"></div>
  
  <!-- Botón enviar -->
  <button 
    type="submit" 
    id="btn-enviar" 
    data-testid="btn-enviar-solicitud"
    disabled>
    Enviar Solicitud
  </button>
</form>

<!-- Toast de éxito -->
<div 
  class="toast success" 
  data-testid="toast-success" 
  style="display:none;">
  Solicitud enviada correctamente
</div>
```

### **Página: mis-solicitudes.html**

**Listado de solicitudes:**
```html
<div class="solicitudes-container">
  <h2>Mis Solicitudes de Préstamo</h2>
  
  <div class="solicitud-card" data-testid="solicitud-card">
    <div class="solicitud-header">
      <span class="numero-solicitud" data-testid="numero-solicitud">
        SOL-20241125-0001
      </span>
      <span class="estado-badge en-revision" data-testid="estado-solicitud">
        En Revisión
      </span>
    </div>
    
    <div class="solicitud-body">
      <p><strong>Monto:</strong> $5,000,000</p>
      <p><strong>Plazo:</strong> 24 meses</p>
      <p><strong>Cuota:</strong> $230,417</p>
      <p>
        <strong>Fecha:</strong> 
        <span data-testid="fecha-solicitud">25/11/2024</span>
      </p>
    </div>
  </div>
</div>
```

### **API Endpoints requeridos:**

```
POST /api/prestamos/calcular
Request: { "monto": 5000000, "plazoMeses": 24 }
Response: {
  "cuotaMensual": 230417,
  "tasaInteres": 1.5,
  "totalPagar": 5530008
}

POST /api/prestamos/solicitar
Request: {
  "userId": 2,
  "monto": 5000000,
  "plazoMeses": 24,
  "proposito": "Vehiculo"
}
Response: {
  "numeroSolicitud": "SOL-20241125-0001",
  "estado": "EN_REVISION",
  "fechaCreacion": "2024-11-25T10:30:00Z"
}

GET /api/prestamos/solicitudes?userId=2
Response: [
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

---

## 🤖 Prompts para Generar el Proyecto con IA

### **Prompt 1: Backend Spring Boot**

```
[ROL] Actúa como desarrollador senior de Spring Boot

[TAREA]
Crea un proyecto Spring Boot completo con estas características:

CONFIGURACIÓN:
- Java 17
- Spring Boot 3.2
- Gradle como gestor de dependencias
- H2 database en memoria
- Puerto: 8080

ENDPOINTS REQUERIDOS:
1. POST /api/auth/login
   - Recibe: { email, password }
   - Valida credenciales contra tabla usuarios
   - Retorna: { token, nombre, userId }

2. GET /api/cuentas/saldo?userId={id}
   - Retorna información de cuenta principal del usuario
   - Formato: { numeroCuenta, saldo, moneda, tipoCuenta }

3. POST /api/prestamos/calcular
   - Recibe: { monto, plazoMeses }
   - Calcula cuota con tasa fija 1.5% mensual
   - Retorna: { cuotaMensual, tasaInteres, totalPagar }

4. POST /api/prestamos/solicitar
   - Recibe: { userId, monto, plazoMeses, proposito }
   - Genera número de solicitud formato SOL-YYYYMMDD-XXXX
   - Guarda en BD y retorna datos de confirmación

5. GET /api/prestamos/solicitudes?userId={id}
   - Retorna lista de solicitudes del usuario

MODELOS DE BASE DE DATOS:
- Usuario (id, email, password, nombre, fechaRegistro)
- Cuenta (id, usuarioId, numeroCuenta, tipo, saldo, fechaApertura)
- SolicitudPrestamo (id, usuarioId, monto, plazoMeses, proposito, cuotaMensual, estado, fechaSolicitud)

DATOS INICIALES (data.sql):
- Usuario 1: test.qa@banco.com / TestQA2024! / QA Tester
- Usuario 2: qa.test@banco.com / CypressTest2024! / Cypress User
- Cuentas con saldos variados
- 1 solicitud previa para testing de listado

REQUISITOS:
- CORS habilitado para http://localhost:3000
- Validaciones de entrada con @Valid
- Manejo de excepciones con @ControllerAdvice
- Logs informativos en cada endpoint
- Sin autenticación JWT (simplificado para testing)

[OUTPUT]
Estructura completa del proyecto con todos los archivos necesarios
```

### **Prompt 2: Frontend HTML + JavaScript**

```
[ROL] Actúa como desarrollador frontend senior

[TAREA]
Crea frontend bancario en HTML + JavaScript vanilla para testing con Selenium/Cypress

PÁGINAS REQUERIDAS:

1. login.html
   - Formulario con email/password
   - Validación de campos requeridos
   - Submit POST a /api/auth/login
   - Al éxito: guardar token en localStorage y redirigir a dashboard

2. dashboard.html
   - Mensaje bienvenida con nombre usuario
   - Menú navegación: Mis Cuentas, Préstamos
   - Botón cerrar sesión

3. consulta-saldo.html
   - GET a /api/cuentas/saldo con userId
   - Mostrar: número cuenta enmascarado, saldo, tipo cuenta
   - Formato saldo: $1,500,000 COP

4. solicitud-prestamo.html
   - Formulario: monto (input number), plazo (select), propósito (radio)
   - Botón "Calcular Cuota" → POST /api/prestamos/calcular
   - Mostrar resultado con cuota, tasa, total
   - Checkbox términos
   - Botón "Enviar Solicitud" (habilitado solo si calculó y aceptó términos)
   - Submit → POST /api/prestamos/solicitar
   - Spinner mientras procesa
   - Toast verde al éxito
   - Redirigir a mis-solicitudes.html

5. mis-solicitudes.html
   - GET /api/prestamos/solicitudes
   - Listado de cards con: número, monto, plazo, cuota, estado, fecha

REQUISITOS CRÍTICOS:
- TODOS los elementos interactivos con data-testid único
- API base URL: http://localhost:8080
- Incluir token en headers: Authorization: Bearer {token}
- Validaciones frontend antes de enviar
- Mensajes de error claros
- CSS básico pero funcional (no usar frameworks)

ESTRUCTURA data-testid (OBLIGATORIO):
- login-email, login-password, login-submit
- nav-cuentas, nav-prestamos
- saldo-amount, cuenta-numero
- input-monto, select-plazo, radio-proposito-vehiculo
- btn-calcular, cuota-mensual, checkbox-terminos
- btn-enviar-solicitud, spinner, toast-success
- solicitud-card, numero-solicitud, estado-solicitud

[OUTPUT]
Archivos HTML + CSS + JS completos y funcionales
```

### **Prompt 3: Tests Selenium Java**

```
[ROL] Actúa como automation engineer senior en Selenium Java

[TAREA]
Crea proyecto de tests con Selenium WebDriver + JUnit 5 + Gradle

CONFIGURACIÓN:
- Java 17
- Selenium 4.16.1
- WebDriverManager 5.6.3 (gestión automática drivers)
- JUnit 5
- AssertJ para assertions

TEST CASE 1: LoginYConsultaSaldoTest
- Login con test.qa@banco.com / TestQA2024!
- Navegar a Mis Cuentas
- Validar saldo = $1,500,000 COP
- Validar número cuenta enmascarado

TEST CASE 2: SolicitudPrestamoTest
- Login con qa.test@banco.com
- Navegar a Préstamos → Nueva Solicitud
- Completar: monto $5M, plazo 24 meses, propósito Vehículo
- Calcular cuota (validar ≈ $230K)
- Aceptar términos
- Enviar solicitud
- Validar toast éxito
- Validar redirección a Mis Solicitudes
- Validar nueva solicitud en listado

REQUISITOS:
- BaseTest con setup/teardown de ChromeDriver
- WebDriverWait con timeouts 10 segundos
- Page Object Model para cada página
- Logs con SLF4J
- Screenshots en fallas
- build.gradle con todas las dependencias

[OUTPUT]
Proyecto Gradle completo con estructura correcta y tests funcionales
```

### **Prompt 4: Tests Cypress**

```
[ROL] Actúa como automation engineer senior en Cypress

[TAREA]
Crea proyecto Cypress con tests E2E completos

CONFIGURACIÓN:
- Cypress 13.6.2
- TypeScript (opcional) o JavaScript
- Fixtures para datos de prueba
- Custom commands para reutilización

TEST SUITE: prestamo.cy.js

TESTS:
1. Login y consulta de saldo
   - cy.login() custom command
   - Navegación a cuentas
   - Validaciones de UI y datos

2. Solicitud de préstamo completa (flujo happy path)
   - cy.intercept() para APIs calcular y solicitar
   - Completar formulario completo
   - Validar cálculo de cuota
   - Submit y validaciones de éxito
   - Validar listado actualizado

3. Validaciones de formulario (edge case)
   - Campos vacíos
   - Monto fuera de rango
   - Sin aceptar términos

ESTRUCTURA:
- cypress/fixtures/prestamo.json (datos prueba)
- cypress/support/commands.js (cy.login, etc)
- cypress/e2e/prestamo.cy.js (tests principales)
- cypress.config.js (baseUrl, timeouts)

REQUISITOS:
- beforeEach con cleanup (cookies, localStorage)
- cy.intercept para todas las APIs
- Assertions múltiples (UI, API, data)
- Screenshots automáticos en fallas
- Video recording habilitado

[OUTPUT]
Proyecto Cypress completo con package.json y tests funcionales
```

---

## 🚀 Pasos para Levantar el Proyecto

### **1. Generar y Levantar Backend**
```bash
# Generar proyecto con IA usando Prompt 1
# Guardar en carpeta: banco-demo-app/backend/

cd backend
./gradlew bootRun

# Backend corriendo en http://localhost:8080
# H2 Console: http://localhost:8080/h2-console
```

### **2. Generar y Levantar Frontend**
```bash
# Generar proyecto con IA usando Prompt 2
# Guardar en carpeta: banco-demo-app/frontend/

cd frontend
npm install
npm start

# Frontend corriendo en http://localhost:3000
```

### **3. Generar y Ejecutar Tests Selenium**
```bash
# Generar proyecto con IA usando Prompt 3
# Guardar en carpeta: banco-demo-app/automation-tests/selenium-java/

cd automation-tests/selenium-java
./gradlew test

# Ver resultados en build/reports/tests/test/index.html
```

### **4. Generar y Ejecutar Tests Cypress**
```bash
# Generar proyecto con IA usando Prompt 4
# Guardar en carpeta: banco-demo-app/automation-tests/cypress/

cd automation-tests/cypress
npm install
npx cypress run

# O modo interactivo:
npx cypress open
```

---

## ✅ Checklist de Validación

### **Backend funcionando:**
- [ ] `curl http://localhost:8080/api/auth/login` responde 200
- [ ] H2 console accesible y con datos iniciales
- [ ] CORS habilitado (frontend puede consumir APIs)

### **Frontend funcionando:**
- [ ] Login acepta credenciales test.qa@banco.com
- [ ] Dashboard muestra mensaje bienvenida
- [ ] Consulta saldo muestra $1,500,000 COP
- [ ] Formulario préstamo calcula cuota correctamente
- [ ] Solicitud se guarda y aparece en listado

### **Elementos para testing:**
- [ ] Todos los inputs tienen data-testid
- [ ] Todos los botones tienen data-testid
- [ ] Mensajes de error/éxito tienen data-testid
- [ ] Elementos de navegación tienen data-testid

### **Tests funcionando:**
- [ ] Selenium ejecuta login + saldo sin errores
- [ ] Selenium completa solicitud préstamo end-to-end
- [ ] Cypress ejecuta todos los tests en modo headless
- [ ] Screenshots se capturan en fallas

---

## 📚 Recursos Adicionales

### **URLs importantes:**
- Backend: http://localhost:8080
- Frontend: http://localhost:3000
- H2 Console: http://localhost:8080/h2-console
- Swagger (si implementas): http://localhost:8080/swagger-ui.html

### **Credenciales de prueba:**
```
Usuario Selenium:
- Email: test.qa@banco.com
- Password: TestQA2024!
- Saldo: $1,500,000

Usuario Cypress:
- Email: qa.test@banco.com  
- Password: CypressTest2024!
- Saldo: $3,000,000
```

---

## 🎯 Resultado Final

Con estos prompts y configuraciones, tendrás:
1. ✅ Aplicación bancaria funcional (Backend + Frontend)
2. ✅ Todos los elementos con data-testid para automatización
3. ✅ Suite de tests Selenium Java completa
4. ✅ Suite de tests Cypress completa
5. ✅ Ambiente listo para practicar Clase 4

**Tiempo estimado de generación con IA:** 30-45 minutos
**Tiempo manual sin IA:** 8-12 horas

---

## 📊 Comparativa Final: Manual vs Automatizado con IA

| Aspecto | Manual | Con IA |
|---------|--------|--------|
| **Tiempo desarrollo** | 2-3 horas/script | 10-15 min/script |
| **Calidad código** | Variable según experiencia | Consistente, best practices |
| **Cobertura assertions** | Suele ser básica | Exhaustiva automáticamente |
| **Documentación** | Mínima o inexistente | Auto-generada con comentarios |
| **Mantenibilidad** | Difícil si no hay estándares | Código limpio y modular |
| **Curva aprendizaje** | Alta (sintaxis framework) | Baja (enfoque en lógica) |

---

## 🎯 Conclusión

La automatización con IA no reemplaza tu conocimiento de testing, lo **amplifica**:

1. **Sigues necesitando entender:** Qué probar, cómo diseñar casos, qué validar
2. **IA acelera:** Escritura de código, aplicación de patterns, cobertura de assertions
3. **Resultado:** Más tests, mejor calidad, menos tiempo

> **La IA es tu copiloto en automatización, tú sigues siendo el piloto que decide qué automatizar y cómo validarlo.**