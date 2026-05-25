package com.venta.Devoluciones.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.venta.Devoluciones.Model.Devolucion;
import com.venta.Devoluciones.Repository.DevolucionRepository;

@Service
public class DevolucionService {

    @Autowired
    private DevolucionRepository repository;

    @Autowired
    private WebClient webClientVentas;

    public List<Devolucion> listar() {
        return repository.findAll();
    }

    public Devolucion guardar(Devolucion devolucion) {

        Boolean ventaDisponible = webClientVentas.get()
                .uri("/ventas")
                .retrieve()
                .bodyToMono(String.class)
                .map(respuesta -> true)
                .onErrorReturn(false)
                .block();

        if (ventaDisponible == false) {
            return null;
        }

        return repository.save(devolucion);
    }

    public Devolucion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}