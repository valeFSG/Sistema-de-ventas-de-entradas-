package com.Venta.Promotores.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Venta.Promotores.DTO.PromotorDTO;
import com.Venta.Promotores.Model.Promotor;
import com.Venta.Promotores.Repository.PromotorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PromotorService {

    @Autowired
    private PromotorRepository promotorRepository;

    @Autowired
    private WebClient webClientEventos;

    public Boolean guardarPromotor(PromotorDTO promotorDTO) {

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

        Promotor promotor = new Promotor();

        promotor.setNombre(promotorDTO.getNombre());
        promotor.setCorreo(promotorDTO.getCorreo());
        promotor.setTelefono(promotorDTO.getTelefono());
        promotor.setComision(promotorDTO.getComision());
        promotor.setEstado(promotorDTO.getEstado());

        promotorRepository.save(promotor);

        return true;
    }

    public List<Promotor> listarPromotores() {
        return promotorRepository.findAll();
    }

    public List<Promotor> obtenerPorEstado(String estado) {
        return promotorRepository.findByEstado(estado);
    }

    public List<Promotor> obtenerPorCorreo(String correo) {
        return promotorRepository.findByCorreo(correo);
    }
}