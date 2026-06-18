package com.venta.Preventa.Controller;

import java.util.List;

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

    @Autowired
    private PreventaService service;

    @GetMapping
    public ResponseEntity<List<Preventa>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preventa> buscarPorId(
            @PathVariable Long id) {

        Preventa preventa = service.buscarPorId(id);

        if (preventa == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(preventa);
    }

    @PostMapping
    public ResponseEntity<Preventa> guardar(
            @Valid @RequestBody PreventaDTO dto) {

        Preventa preventa = new Preventa();

        preventa.setCliente(dto.getCliente());
        preventa.setEventoId(dto.getEventoId());
        preventa.setCantidadEntradas(dto.getCantidadEntradas());
        preventa.setTotal(dto.getTotal());
        preventa.setEstado(dto.getEstado());

        Preventa preventaGuardada = service.guardar(preventa);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(preventaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preventa> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PreventaDTO dto) {

        Preventa p = service.buscarPorId(id);

        if (p == null) {
            return ResponseEntity.notFound().build();
        }

        p.setCliente(dto.getCliente());
        p.setEventoId(dto.getEventoId());
        p.setCantidadEntradas(dto.getCantidadEntradas());
        p.setTotal(dto.getTotal());
        p.setEstado(dto.getEstado());

        Preventa actualizada = service.guardar(p);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        Preventa preventa = service.buscarPorId(id);

        if (preventa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Preventa no encontrada");
        }

        service.eliminar(id);

        return ResponseEntity.ok(
                "Preventa eliminada correctamente");
    }
}