package com.Venta.Validacion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Venta.Validacion.DTO.DTO;
import com.Venta.Validacion.Model.Validacion;
import com.Venta.Validacion.Repository.ValidacionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ValidacionService {

    @Autowired
    private ValidacionRepository validacionRepository;

    @Autowired
    private WebClient webClientTickets;

    public Boolean guardarValidacion(DTO validacionDTO) {

        Boolean ticketDisponible = webClientTickets.get()
                .uri("/tickets/listar")
                .retrieve()
                .bodyToMono(String.class)
                .map(respuesta -> true)
                .onErrorReturn(false)
                .block();

        if (ticketDisponible == false) {
            return false;
        }

        Validacion validacion = new Validacion();

        validacion.setTicketId(validacionDTO.getTicketId());
        validacion.setCodigoEntrada(validacionDTO.getCodigoEntrada());
        validacion.setEstado(validacionDTO.getEstado());
        validacion.setFechaValidacion(validacionDTO.getFechaValidacion());
        validacion.setObservacion(validacionDTO.getObservacion());

        validacionRepository.save(validacion);

        return true;
    }

    public List<Validacion> listarValidaciones() {
        return validacionRepository.findAll();
    }

    public List<Validacion> obtenerPorTicket(Integer ticketId) {
        return validacionRepository.findByTicketId(ticketId);
    }

    public List<Validacion> obtenerPorEstado(String estado) {
        return validacionRepository.findByEstado(estado);
    }

    public List<Validacion> obtenerPorCodigoEntrada(String codigoEntrada) {
        return validacionRepository.findByCodigoEntrada(codigoEntrada);
    }
}