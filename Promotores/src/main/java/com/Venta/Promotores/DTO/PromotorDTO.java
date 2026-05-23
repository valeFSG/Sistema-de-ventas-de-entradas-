package com.Venta.Promotores.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotorDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo debe tener un formato válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @NotNull(message = "La comisión es obligatoria")
    private Double comision;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}
