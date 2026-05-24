package com.Venta.Tickets.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private Long id;
    private String cliente;
    private String evento;
    private Double precio;
    private Integer cantidad;
    private Long ventaId;
}