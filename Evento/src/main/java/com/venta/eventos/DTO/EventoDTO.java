package com.venta.eventos.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EventoDTO {
    private String nombre;
    private String categoria;
    private LocalDate fecha;
    private String lugar;
    private Integer capacidad;


}
