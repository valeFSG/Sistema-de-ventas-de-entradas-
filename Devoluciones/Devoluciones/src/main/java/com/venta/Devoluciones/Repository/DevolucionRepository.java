package com.venta.Devoluciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venta.Devoluciones.Model.Devolucion;

public interface DevolucionRepository extends JpaRepository<Devolucion, Long>{

}