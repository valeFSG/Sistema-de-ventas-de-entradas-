package com.venta.ventas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.venta.ventas.Model.Venta;
import com.venta.ventas.Repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repository;

    @Autowired
    private WebClient webClient;

    public List<Venta> listar(){
        return repository.findAll();
    }

    public Venta guardar(Venta venta){

    Object evento = webClient.get()
            .uri("http://localhost:8081/evento/" + venta.getEventoId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();

    if(evento == null){
        throw new RuntimeException("Evento no encontrado");
    }

        return repository.save(venta);
    }

    public Venta buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}