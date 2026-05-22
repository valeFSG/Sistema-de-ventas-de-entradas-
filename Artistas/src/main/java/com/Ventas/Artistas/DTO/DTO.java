package com.Ventas.Artistas.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTO {
    private String nombreArtistico;
    private String especialidad;
    private String biografia;
    private LocalDateTime fechaDisponible;
    private Boolean disponible;
}
