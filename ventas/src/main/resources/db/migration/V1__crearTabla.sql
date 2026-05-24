CREATE TABLE venta(

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(255) NOT NULL,
    evento_id BIGINT NOT NULL,
    cantidad_entradas INT NOT NULL,
    total DOUBLE NOT NULL,
    metodo_pago VARCHAR(100) NOT NULL

);