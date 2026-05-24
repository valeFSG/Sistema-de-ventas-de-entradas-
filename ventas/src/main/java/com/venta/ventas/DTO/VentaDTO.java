package com.venta.ventas.DTO;

import lombok.Data;

@Data
public class VentaDTO {

    private Long id;
    private String cliente;
    private String evento;
    private Integer cantidadEntradas;
    private Double total;
    private String metodoPago;
    private Long eventoId;
}