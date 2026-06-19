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

        log.info("INICIO GET /evento - Listando todos los eventos");

        List<Evento> eventos = service.listar();

        log.info("FIN GET /evento - Se encontraron {} eventos", eventos.size());

        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {

        log.info("INICIO GET /evento/{} - Buscando evento por ID", id);

        Evento evento = service.buscarPorId(id);

        if (evento == null) {
            log.warn("FIN GET /evento/{} - Evento no encontrado", id);
            return ResponseEntity.notFound().build();
        }

        log.info("FIN GET /evento/{} - Evento encontrado correctamente", id);

        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<Evento> guardar(
            @Valid @RequestBody EventoDTO dto) {

        log.info("INICIO POST /evento - Creando evento con nombre: {}", dto.getNombre());

        Evento evento = new Evento();

        evento.setNombre(dto.getNombre());
        evento.setCategoria(dto.getCategoria());
        evento.setFecha(dto.getFecha());
        evento.setLugar(dto.getLugar());
        evento.setCapacidad(dto.getCapacidad());
        evento.setRecintoId(dto.getRecintoId());

        Evento eventoGuardado = service.guardar(evento);

        log.info("FIN POST /evento - Evento creado correctamente con ID: {}", eventoGuardado.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EventoDTO dto) {

        log.info("INICIO PUT /evento/{} - Actualizando evento", id);

        Evento evento = service.buscarPorId(id);

        if (evento == null) {
            log.warn("FIN PUT /evento/{} - Evento no encontrado, no se pudo actualizar", id);
            return ResponseEntity.notFound().build();
        }

        evento.setNombre(dto.getNombre());
        evento.setCategoria(dto.getCategoria());
        evento.setFecha(dto.getFecha());
        evento.setLugar(dto.getLugar());
        evento.setCapacidad(dto.getCapacidad());
        evento.setRecintoId(dto.getRecintoId());

        Evento actualizado = service.guardar(evento);

        log.info("FIN PUT /evento/{} - Evento actualizado correctamente", id);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        log.warn("INICIO DELETE /evento/{} - Intentando eliminar evento", id);

        Evento evento = service.buscarPorId(id);

        if (evento == null) {
            log.warn("FIN DELETE /evento/{} - Evento no encontrado, no se pudo eliminar", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Evento no encontrado");
        }

        service.eliminar(id);

        log.info("FIN DELETE /evento/{} - Evento eliminado correctamente", id);

        return ResponseEntity.ok("Evento eliminado correctamente");
    }
}