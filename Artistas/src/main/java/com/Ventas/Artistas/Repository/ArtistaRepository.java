package com.Ventas.Artistas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ventas.Artistas.Model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    List<Artista> findByEspecialidad(String especialidad);
    List<Artista> findByDisponible(Boolean disponible);
}