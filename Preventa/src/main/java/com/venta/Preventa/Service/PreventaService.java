package com.venta.Preventa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venta.Preventa.Model.Preventa;
import com.venta.Preventa.Repository.PreventaRepository;
import org.springframework.web.reactive.function.client.WebClient;;

@Service
public class PreventaService {

    @Autowired
    private PreventaRepository repository;

    @Autowired
    private WebClient webClient;

    public List<Preventa> listar(){
        return repository.findAll();
    }

    public Preventa guardar(Preventa preventa){

    Object evento = webClient.get()
            .uri("http://localhost:8081/evento/" + preventa.getEventoId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();

    if(evento == null){
        throw new RuntimeException("Evento no encontrado");
    }

    return repository.save(preventa);
}

    public Preventa buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}