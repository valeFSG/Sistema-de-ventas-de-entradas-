package com.venta.Streaming.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreamingDTO {

    @NotNull(message = "El ID del evento es obligatorio")
    private Integer eventoId;

    @NotBlank(message = "La plataforma es obligatoria")
    private String plataforma;

    @NotBlank(message = "La URL de acceso es obligatoria")
    private String urlAcceso;

    @NotBlank(message = "El código de acceso es obligatorio")
    private String codigoAcceso;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDateTime fechaFin;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}