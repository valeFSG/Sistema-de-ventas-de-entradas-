package com.venta.ventas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venta.ventas.Model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{

}