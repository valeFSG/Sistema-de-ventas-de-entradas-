package com.venta.Preventa.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.venta.Preventa.DTO.PreventaDTO;
import com.venta.Preventa.Model.Preventa;
import com.venta.Preventa.Service.PreventaService;

@RestController
@RequestMapping("/preventa")
public class PreventaController {

    private static final Logger log = LoggerFactory.getLogger(PreventaController.class);

    @Autowired
    private PreventaService service;

    @GetMapping
    public ResponseEntity<List<Preventa>> listar() {

        log.info("INICIO GET /preventa - Listando todas las preventas");

        List<Preventa> preventas = service.listar();

        log.info("FIN GET /preventa - Se encontraron {} preventas", preventas.size());

        return ResponseEntity.ok(preventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preventa> buscarPorId(
            @PathVariable Long id) {

        log.info("INICIO GET /preventa/{} - Buscando preventa por ID", id);

        Preventa preventa = service.buscarPorId(id);

        if (preventa == null) {
            log.warn("FIN GET /preventa/{} - Preventa no encontrada", id);
            return ResponseEntity.notFound().build();
        }

        log.info("FIN GET /preventa/{} - Preventa encontrada correctamente", id);

        return ResponseEntity.ok(preventa);
    }

    @PostMapping
    public ResponseEntity<Preventa> guardar(
            @Valid @RequestBody PreventaDTO dto) {

        log.info("INICIO POST /preventa - Creando preventa para cliente: {}", dto.getCliente());

        Preventa preventa = new Preventa();

        preventa.setCliente(dto.getCliente());
        preventa.setEventoId(dto.getEventoId());
        preventa.setCantidadEntradas(dto.getCantidadEntradas());
        preventa.setTotal(dto.getTotal());
        preventa.setEstado(dto.getEstado());

        Preventa preventaGuardada = service.guardar(preventa);

        log.info("FIN POST /preventa - Preventa creada correctamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(preventaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preventa> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PreventaDTO dto) {

        log.info("INICIO PUT /preventa/{} - Actualizando preventa", id);

        Preventa p = service.buscarPorId(id);

        if (p == null) {
            log.warn("FIN PUT /preventa/{} - Preventa no encontrada, no se pudo actualizar", id);
            return ResponseEntity.notFound().build();
        }

        p.setCliente(dto.getCliente());
        p.setEventoId(dto.getEventoId());
        p.setCantidadEntradas(dto.getCantidadEntradas());
        p.setTotal(dto.getTotal());
        p.setEstado(dto.getEstado());

        Preventa actualizada = service.guardar(p);

        log.info("FIN PUT /preventa/{} - Preventa actualizada correctamente", id);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        log.info("INICIO DELETE /preventa/{} - Eliminando preventa", id);

        Preventa preventa = service.buscarPorId(id);

        if (preventa == null) {
            log.warn("FIN DELETE /preventa/{} - Preventa no encontrada, no se pudo eliminar", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Preventa no encontrada");
        }

        service.eliminar(id);

        log.info("FIN DELETE /preventa/{} - Preventa eliminada correctamente", id);

        return ResponseEntity.ok(
                "Preventa eliminada correctamente");
    }
}