package com.Venta.Promotores.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Venta.Promotores.Model.Promotor;

@Repository
public interface PromotorRepository extends JpaRepository<Promotor, Integer> {

    List<Promotor> findByEstado(String estado);

    List<Promotor> findByCorreo(String correo);
}