package com.venta.eventos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.venta.eventos.Model.Evento;
import com.venta.eventos.Repository.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository repository;

    @Autowired
    private WebClient webClient;

    public List<Evento> listar(){
        return repository.findAll();
    }

    public Evento guardar(Evento evento){

    Object recinto = webClient.get()
            .uri("http://localhost:8082/recinto/" + evento.getRecintoId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();

    if(recinto == null){
        throw new RuntimeException("Recinto no encontrado");
    }

    return repository.save(evento);
}

    public Evento buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){ 
        repository.deleteById(id);
    }

}
