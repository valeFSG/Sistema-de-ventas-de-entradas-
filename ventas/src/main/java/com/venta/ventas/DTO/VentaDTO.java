package com.venta.ventas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class VentaDTO {

    private Long id;

    @NotBlank(message = "El cliente es obligatorio")
    private String cliente;

    @NotBlank(message = "El evento es obligatorio")
    private String evento;

    @NotNull(message = "La cantidad de entradas es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidadEntradas;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a 0")
    private Double total;

    @NotBlank(message = "El metodo de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "El eventoId es obligatorio")
    private Long eventoId;
}