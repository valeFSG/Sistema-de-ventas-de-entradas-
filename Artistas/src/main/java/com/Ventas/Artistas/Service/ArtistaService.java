package com.Ventas.Artistas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Ventas.Artistas.DTO.DTO;
import com.Ventas.Artistas.Model.Artista;
import com.Ventas.Artistas.Repository.ArtistaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private WebClient webClientEventos;

    public Boolean guardarArtista(DTO artistaDTO) {

        Boolean eventoDisponible = webClientEventos.get()
                .uri("/evento")
                .retrieve()
                .bodyToMono(String.class)
                .map(respuesta -> true)
                .onErrorReturn(false)
                .block();

        if (eventoDisponible == false) {
            return false;
        }

        Artista artista = new Artista();

        artista.setNombreArtistico(artistaDTO.getNombreArtistico());
        artista.setEspecialidad(artistaDTO.getEspecialidad());
        artista.setBiografia(artistaDTO.getBiografia());
        artista.setFechaDisponible(artistaDTO.getFechaDisponible());
        artista.setDisponible(artistaDTO.getDisponible());

        artistaRepository.save(artista);

        return true;
    }

    public List<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    public List<Artista> obtenerPorEspecialidad(String especialidad) {
        return artistaRepository.findByEspecialidad(especialidad);
    }

    public List<Artista> obtenerPorDisponibilidad(Boolean disponible) {
        return artistaRepository.findByDisponible(disponible);
    }
}