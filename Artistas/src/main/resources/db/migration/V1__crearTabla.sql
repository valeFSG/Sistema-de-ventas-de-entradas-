CREATE TABLE artista (
    id INT NOT NULL AUTO_INCREMENT,
    nombre_artistico VARCHAR(255) NOT NULL,
    especialidad VARCHAR(255) NOT NULL,
    biografia VARCHAR(255),
    fecha_disponible DATETIME,
    disponible BOOLEAN,
    PRIMARY KEY (id)
);