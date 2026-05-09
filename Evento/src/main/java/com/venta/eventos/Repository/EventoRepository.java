package com.venta.eventos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venta.eventos.Model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
