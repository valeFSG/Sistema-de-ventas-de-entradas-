CREATE TABLE preventa(

    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente VARCHAR(255) NOT NULL,
    evento_id BIGINT NOT NULL,
    cantidad_entradas INT NOT NULL,
    total DOUBLE NOT NULL,
    estado VARCHAR(100) NOT NULL

);