-- Asegurar que la tabla exista antes del INSERT
CREATE TABLE IF NOT EXISTS USUARIO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

-- Tus inserts del examen
INSERT INTO USUARIO (correo, contrasena, rol) VALUES 
('admin@test.com', '$2a$10$HASH_ADMIN', 'ROLE_ADMIN'), 
('supervisor@test.com', '$2a$10$HASH_SUPERVISOR', 'ROLE_SUPERVISOR'), 
('usuario@test.com', '$2a$10$HASH_USUARIO', 'ROLE_USUARIO');