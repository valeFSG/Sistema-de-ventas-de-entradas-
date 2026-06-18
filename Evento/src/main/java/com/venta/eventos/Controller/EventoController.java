package com.venta.eventos.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venta.eventos.DTO.EventoDTO;
import com.venta.eventos.Model.Evento;
import com.venta.eventos.Service.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/evento")
public class EventoController {

    private static final Logger log = LoggerFactory.getLogger(EventoController.class);

    @Autowired
    private EventoService service;

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {

        log.info("Llamando a listar todos");

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {

        Evento evento = service.buscarPorId(id);

        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<Evento> guardar(
            @Valid @RequestBody EventoDTO dto) {

        Evento evento = new Evento();

        evento.setNombre(dto.getNombre());
        evento.setCategoria(dto.getCategoria());
        evento.setFecha(dto.getFecha());
        evento.setLugar(dto.getLugar());
        evento.setCapacidad(dto.getCapacidad());
        evento.setRecintoId(dto.getRecintoId());

        Evento eventoGuardado = service.guardar(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EventoDTO dto) {

        Evento evento = service.buscarPorId(id);

        if (evento == null) {
            return ResponseEntity.notFound().build();
        }

        evento.setNombre(dto.getNombre());
        evento.setCategoria(dto.getCategoria());
        evento.setFecha(dto.getFecha());
        evento.setLugar(dto.getLugar());
        evento.setCapacidad(dto.getCapacidad());
        evento.setRecintoId(dto.getRecintoId());

        Evento actualizado = service.guardar(evento);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Evento evento = service.buscarPorId(id);

        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Evento no encontrado");
        }

        service.eliminar(id);

        return ResponseEntity.ok("Evento eliminado correctamente");
    }
}
