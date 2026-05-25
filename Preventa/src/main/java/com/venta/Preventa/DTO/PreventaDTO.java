package com.venta.Preventa.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreventaDTO {

    private Long id;

    @NotBlank(message = "El cliente es obligatorio")
    private String cliente;

    @NotNull(message = "El eventoId es obligatorio")
    private Long eventoId;

    @NotNull(message = "La cantidad de entradas es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidadEntradas;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a 0")
    private Double total;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}