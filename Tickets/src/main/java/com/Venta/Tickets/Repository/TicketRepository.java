package com.Venta.Tickets.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Venta.Tickets.Model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCliente(String cliente);
    
    List<Ticket> findByEvento(String evento);
}