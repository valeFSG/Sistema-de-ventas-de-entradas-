package com.Venta.Validacion.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO {

    @NotNull(message = "El ID del ticket es obligatorio")
    private Integer ticketId;

    @NotBlank(message = "El código de entrada es obligatorio")
    private String codigoEntrada;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de validación es obligatoria")
    private LocalDateTime fechaValidacion;

    private String observacion;
}
