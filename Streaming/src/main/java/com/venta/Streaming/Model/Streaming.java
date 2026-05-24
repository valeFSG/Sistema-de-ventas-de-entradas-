package com.venta.Streaming.Model;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "streaming")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer eventoId;

    private String plataforma;

    private String urlAcceso;

    private String codigoAcceso;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private Boolean activo;
}