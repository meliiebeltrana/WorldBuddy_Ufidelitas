/* Crear la base de datos del proyecto */
CREATE DATABASE worldbuddy_db;

/* Usar la base de datos */
USE worldbuddy_db;

/* Crear usuario para que la aplicación se conecte */
CREATE USER 'worldbuddy_user'@'localhost' IDENTIFIED BY '1234';

/* Dar permisos al usuario sobre la base de datos */
GRANT ALL PRIVILEGES ON worldbuddy_db.* TO 'worldbuddy_user'@'localhost';

/* Aplicar cambios */
FLUSH PRIVILEGES;


/* Tabla de países */
CREATE TABLE pais (
    id_pais INT AUTO_INCREMENT PRIMARY KEY,
    nombre_pais VARCHAR(100) NOT NULL
);

/* Tabla de ciudades */
CREATE TABLE ciudad (
    id_ciudad INT AUTO_INCREMENT PRIMARY KEY,
    nombre_ciudad VARCHAR(100) NOT NULL,
    hora VARCHAR(10) NOT NULL,
    clima VARCHAR(50) NOT NULL,
    temperatura DECIMAL(5,2) NOT NULL,
    dia_noche VARCHAR(10) NOT NULL,
    id_pais INT NOT NULL,
    CONSTRAINT fk_ciudad_pais
        FOREIGN KEY (id_pais) REFERENCES pais(id_pais)
);

/* Tabla de usuarios */
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL
);

/* Tabla de favoritos */
CREATE TABLE favorito (
    id_favorito INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_ciudad INT NOT NULL,
    CONSTRAINT fk_favorito_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    CONSTRAINT fk_favorito_ciudad
        FOREIGN KEY (id_ciudad) REFERENCES ciudad(id_ciudad)
);


/* Insertar 5 países */
INSERT INTO pais (nombre_pais) VALUES
('Costa Rica'),
('España'),
('Japón'),
('Estados Unidos'),
('Argentina');


/* Insertar 5 ciudades */
INSERT INTO ciudad (nombre_ciudad, hora, clima, temperatura, dia_noche, id_pais) VALUES
('San José', '12:30', 'Soleado', 28.50, 'Día', 1),
('Madrid', '19:30', 'Nublado', 18.00, 'Noche', 2),
('Tokio', '03:30', 'Lluvioso', 22.00, 'Noche', 3),
('Nueva York', '13:30', 'Ventoso', 16.50, 'Día', 4),
('Buenos Aires', '15:30', 'Despejado', 24.00, 'Día', 5);

/* Rol para los usuarios */
ALTER TABLE usuario
ADD rol VARCHAR(20) NOT NULL;

/* Insertar usuarios con roles */
INSERT INTO usuario (nombre_usuario, correo, contrasena, rol) VALUES
('Administrador', 'admin@gmail.com', '123', 'ADMIN'),
('Usuario Normal', 'user@gmail.com', '123', 'USER'),
('Juan', 'juan@gmail.com', '1234', 'USER'),
('María', 'maria@gmail.com', '1234', 'USER'),
('Carlos', 'carlos@gmail.com', '1234', 'USER'),
('Ana', 'ana@gmail.com', '1234', 'USER'),
('Luis', 'luis@gmail.com', '1234', 'USER');

/* Insertar favoritos (ya con usuarios existentes) */
INSERT INTO favorito (id_usuario, id_ciudad) VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 5),
(4, 4);







/* Aplicar los cambios */
FLUSH PRIVILEGES;
