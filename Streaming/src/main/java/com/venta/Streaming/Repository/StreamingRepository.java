package com.venta.Streaming.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venta.Streaming.Model.Streaming;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Integer> {

    List<Streaming> findByEventoId(Integer eventoId);

    List<Streaming> findByActivo(Boolean activo);

    List<Streaming> findByCodigoAcceso(String codigoAcceso);
}
