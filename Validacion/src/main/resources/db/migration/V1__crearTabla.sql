CREATE TABLE validacion (
    id INT NOT NULL AUTO_INCREMENT,
    ticket_id INT NOT NULL,
    codigo_entrada VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    fecha_validacion DATETIME NOT NULL,
    observacion VARCHAR(255),
    PRIMARY KEY (id)
);