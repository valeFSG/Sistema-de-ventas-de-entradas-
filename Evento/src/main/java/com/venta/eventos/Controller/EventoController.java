package com.venta.eventos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venta.eventos.DTO.EventoDTO;
import com.venta.eventos.Model.Evento;
import com.venta.eventos.Service.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService service;

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Evento> guardar(@RequestBody EventoDTO dto) {

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

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarPorId(@PathVariable Long id) {

        Evento evento = service.buscarPorId(id);

        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.ok("Evento eliminado correctamente");
    }

}