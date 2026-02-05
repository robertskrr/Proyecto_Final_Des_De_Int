DROP DATABASE IF EXISTS gestion_pedidos;

CREATE DATABASE gestion_pedidos CHARACTER SET utf8mb4;
USE gestion_pedidos;

CREATE TABLE IF NOT EXISTS pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    producto VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL,
    fecha_pedido DATE NOT NULL
);