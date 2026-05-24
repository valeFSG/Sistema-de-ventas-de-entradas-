package com.venta.Preventa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preventa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preventa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    private Long  eventoId;

    private Integer cantidadEntradas;

    private Double total;

    private String estado;
}
