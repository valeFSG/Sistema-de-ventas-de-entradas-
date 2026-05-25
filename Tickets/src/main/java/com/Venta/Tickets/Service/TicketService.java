package com.Venta.Tickets.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Venta.Tickets.DTO.TicketDTO;
import com.Venta.Tickets.Model.Ticket;
import com.Venta.Tickets.Repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private WebClient webClient;

    public Boolean guardarTicket(TicketDTO ticketDTO) {

        Object venta = webClient.get()
                .uri("http://localhost:8091/ventas/" + ticketDTO.getVentaId())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        if (venta == null) {
            throw new RuntimeException("Venta no encontrada");
        }

        Ticket ticket = new Ticket();

        ticket.setCliente(ticketDTO.getCliente());
        ticket.setEvento(ticketDTO.getEvento());
        ticket.setPrecio(ticketDTO.getPrecio());
        ticket.setCantidad(ticketDTO.getCantidad());
        ticket.setVentaId(ticketDTO.getVentaId());

        ticketRepository.save(ticket);

        return true;
    }

    public List<Ticket> obtenerPorCliente(String cliente) {

        return ticketRepository.findByCliente(cliente);
    }

    public List<Ticket> obtenerPorEvento(String evento) {

        return ticketRepository.findByEvento(evento);
    }

    public List<Ticket> listarTickets() {

        return ticketRepository.findAll();
    }

    public Ticket buscarPorId(Long id) {

        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket guardar(Ticket ticket) {

        return ticketRepository.save(ticket);
    }

    public void eliminar(Long id) {

        ticketRepository.deleteById(id);
    }
}