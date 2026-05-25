package com.Venta.Tickets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Ticket creado correctamente");
    }

    @GetMapping("/buscar-cliente/{cliente}")
    public ResponseEntity<List<Ticket>> buscarPorCliente(
            @PathVariable String cliente) {

        return ResponseEntity.ok(
                ticketService.obtenerPorCliente(cliente)
        );
    }

    @GetMapping("/buscar-evento/{evento}")
    public ResponseEntity<List<Ticket>> buscarPorEvento(
            @PathVariable String evento) {

        return ResponseEntity.ok(
                ticketService.obtenerPorEvento(evento)
        );
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Ticket>> listarTickets() {

        return ResponseEntity.ok(
                ticketService.listarTickets()
        );
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Ticket> actualizarTicket(
            @PathVariable Long id,
            @RequestBody Ticket ticket){

        Ticket t = ticketService.buscarPorId(id);

        t.setCliente(ticket.getCliente());
        t.setEvento(ticket.getEvento());
        t.setPrecio(ticket.getPrecio());
        t.setCantidad(ticket.getCantidad());
        t.setVentaId(ticket.getVentaId());

        Ticket actualizado = ticketService.guardar(t);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTicket(
            @PathVariable Long id){

        ticketService.eliminar(id);

        return ResponseEntity.ok(
                "Ticket eliminado correctamente"
        );
    }
}