CREATE TABLE streaming (
    id INT NOT NULL AUTO_INCREMENT,
    evento_id INT NOT NULL,
    plataforma VARCHAR(255) NOT NULL,
    url_acceso VARCHAR(255) NOT NULL,
    codigo_acceso VARCHAR(255) NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    activo BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);