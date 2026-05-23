package com.venta.Devoluciones.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionDTO {

    private Long id;
    private String cliente;
    private String evento;
    private String motivo;
    private Double monto;
    private String estado;
}