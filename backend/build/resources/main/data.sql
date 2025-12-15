-- Insertar usuarios de prueba
INSERT INTO usuario (id, email, password, nombre, fecha_registro) VALUES
(1, 'test.qa@banco.com', 'TestQA2024!', 'QA Tester', '2023-01-15'),
(2, 'qa.test@banco.com', 'CypressTest2024!', 'Cypress User', '2023-02-20');

-- Insertar cuentas bancarias
INSERT INTO cuenta (id, usuario_id, numero_cuenta, tipo, saldo, fecha_apertura) VALUES
(1, 1, '1234567890', 'AHORROS', 1500000.00, '2023-01-20'),
(2, 1, '0987654321', 'CORRIENTE', 500000.00, '2023-03-10'),
(3, 2, '5555666677', 'AHORROS', 3000000.00, '2023-02-25');

-- Insertar solicitud de préstamo previa (para testing de listado)
INSERT INTO solicitud_prestamo (id, usuario_id, monto, plazo_meses, proposito, cuota_mensual, estado, fecha_solicitud) VALUES
(1, 2, 5000000.00, 24, 'Vehiculo', 230417.00, 'EN_REVISION', '2024-11-15');
