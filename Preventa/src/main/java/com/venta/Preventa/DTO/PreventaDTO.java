package com.venta.Preventa.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreventaDTO {

    private Long id;
    private String cliente;
    private String evento;
    private Integer cantidadEntradas;
    private Double total;
    private String estado;
}