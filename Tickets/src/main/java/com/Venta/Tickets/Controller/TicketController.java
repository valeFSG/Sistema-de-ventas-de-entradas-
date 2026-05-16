package com.Venta.Tickets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Venta.Tickets.DTO.TicketDTO;
import com.Venta.Tickets.Model.Ticket;
import com.Venta.Tickets.Service.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/crear-ticket")
    public ResponseEntity<String> crearTicket(@RequestBody TicketDTO ticket) {

        Boolean save = ticketService.guardarTicket(ticket);

        if (save != true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el ticket.");
        }

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/buscar-cliente/{cliente}")
    public List<Ticket> buscarPorCliente(@PathVariable String cliente) {
        return ticketService.obtenerPorCliente(cliente);
    }

    @GetMapping("/buscar-evento/{evento}")
    public List<Ticket> buscarPorEvento(@PathVariable String evento) {
        return ticketService.obtenerPorEvento(evento);
    }

    @GetMapping("/listar")
    public List<Ticket> listarTickets() {
        return ticketService.listarTickets();
    }
}