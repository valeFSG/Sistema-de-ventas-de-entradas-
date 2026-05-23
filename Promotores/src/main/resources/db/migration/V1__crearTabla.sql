CREATE TABLE promotor (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL,
    comision DOUBLE NOT NULL,
    estado VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);