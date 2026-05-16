package com.Venta.Recintos.DTO;

import lombok.Data;

@Data
public class RecintoDTO {

    private String nombre;
    private String direccion;
    private String ciudad;
    private Integer capacidad;
    private String tipo;
}