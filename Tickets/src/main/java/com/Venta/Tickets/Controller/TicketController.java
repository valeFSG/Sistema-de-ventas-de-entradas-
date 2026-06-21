package com.Venta.Tickets.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.Venta.Tickets.DTO.TicketDTO;
import com.Venta.Tickets.Model.Ticket;
import com.Venta.Tickets.Service.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @PostMapping("/crear-ticket")
    public ResponseEntity<String> crearTicket(
            @Valid @RequestBody TicketDTO ticket) {

        log.info("INICIO POST /api/v1/tickets/crear-ticket - Creando ticket para cliente: {}", ticket.getCliente());

        Boolean save = ticketService.guardarTicket(ticket);

        if (save != true) {
            log.warn("FIN POST /api/v1/tickets/crear-ticket - No se pudo crear el ticket");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el ticket.");
        }

        log.info("FIN POST /api/v1/tickets/crear-ticket - Ticket creado correctamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Ticket creado correctamente");
    }

    @GetMapping("/buscar-cliente/{cliente}")
    public ResponseEntity<List<Ticket>> buscarPorCliente(
            @PathVariable String cliente) {

        log.info("INICIO GET /api/v1/tickets/buscar-cliente/{} - Buscando tickets por cliente", cliente);

        List<Ticket> tickets = ticketService.obtenerPorCliente(cliente);

        log.info("FIN GET /api/v1/tickets/buscar-cliente/{} - Se encontraron {} tickets", cliente, tickets.size());

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/buscar-evento/{evento}")
    public ResponseEntity<List<Ticket>> buscarPorEvento(
            @PathVariable String evento) {

        log.info("INICIO GET /api/v1/tickets/buscar-evento/{} - Buscando tickets por evento", evento);

        List<Ticket> tickets = ticketService.obtenerPorEvento(evento);

        log.info("FIN GET /api/v1/tickets/buscar-evento/{} - Se encontraron {} tickets", evento, tickets.size());

        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Ticket>> listarTickets() {

        log.info("INICIO GET /api/v1/tickets/listar - Listando todos los tickets");

        List<Ticket> tickets = ticketService.listarTickets();

        log.info("FIN GET /api/v1/tickets/listar - Se encontraron {} tickets", tickets.size());

        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Ticket> actualizarTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketDTO dto) {

        log.info("INICIO PUT /api/v1/tickets/actualizar/{} - Actualizando ticket", id);

        Ticket t = ticketService.buscarPorId(id);

        if (t == null) {
            log.warn("FIN PUT /api/v1/tickets/actualizar/{} - Ticket no encontrado, no se pudo actualizar", id);
            return ResponseEntity.notFound().build();
        }

        t.setCliente(dto.getCliente());
        t.setEvento(dto.getEvento());
        t.setPrecio(dto.getPrecio());
        t.setCantidad(dto.getCantidad());
        t.setVentaId(dto.getVentaId());

        Ticket actualizado = ticketService.guardar(t);

        log.info("FIN PUT /api/v1/tickets/actualizar/{} - Ticket actualizado correctamente", id);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTicket(
            @PathVariable Long id) {

        log.info("INICIO DELETE /api/v1/tickets/eliminar/{} - Eliminando ticket", id);

        Ticket ticket = ticketService.buscarPorId(id);

        if (ticket == null) {
            log.warn("FIN DELETE /api/v1/tickets/eliminar/{} - Ticket no encontrado, no se pudo eliminar", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ticket no encontrado");
        }

        ticketService.eliminar(id);

        log.info("FIN DELETE /api/v1/tickets/eliminar/{} - Ticket eliminado correctamente", id);

        return ResponseEntity.ok(
                "Ticket eliminado correctamente"
        );
    }
}