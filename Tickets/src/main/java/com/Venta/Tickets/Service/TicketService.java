package com.Venta.Tickets.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Venta.Tickets.DTO.TicketDTO;
import com.Venta.Tickets.Model.Ticket;
import com.Venta.Tickets.Repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Boolean guardarTicket(TicketDTO ticketDTO) {

        Ticket ticket = new Ticket();

        ticket.setCliente(ticketDTO.getCliente());
        ticket.setEvento(ticketDTO.getEvento());
        ticket.setPrecio(ticketDTO.getPrecio());
        ticket.setCantidad(ticketDTO.getCantidad());

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
}