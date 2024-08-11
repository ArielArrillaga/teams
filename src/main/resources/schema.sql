-- Crear la tabla "users"
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(10) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Crear la tabla "clubs"
CREATE TABLE IF NOT EXISTS clubs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    liga VARCHAR(50) NOT NULL,
    pais VARCHAR(50) NOT NULL
);
