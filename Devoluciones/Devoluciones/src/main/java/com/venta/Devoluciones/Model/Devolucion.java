package com.venta.Devoluciones.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devolucion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;

    private String evento;

    private String motivo;

    private Double monto;

    private String estado;
}