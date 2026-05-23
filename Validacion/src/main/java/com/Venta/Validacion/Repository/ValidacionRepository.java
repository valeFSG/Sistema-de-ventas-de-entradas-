package com.Venta.Validacion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Venta.Validacion.Model.Validacion;

@Repository
public interface ValidacionRepository extends JpaRepository<Validacion, Integer> {

    List<Validacion> findByTicketId(Integer ticketId);

    List<Validacion> findByEstado(String estado);

    List<Validacion> findByCodigoEntrada(String codigoEntrada);
}
