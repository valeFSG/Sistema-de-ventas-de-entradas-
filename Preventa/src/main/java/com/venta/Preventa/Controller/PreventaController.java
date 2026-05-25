package com.venta.Preventa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venta.Preventa.Model.Preventa;
import com.venta.Preventa.Service.PreventaService;

@RestController
@RequestMapping("/preventa")
public class PreventaController {

    @Autowired
    private PreventaService service;

    @GetMapping
    public ResponseEntity<List<Preventa>> listar(){

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Preventa> buscarPorId(
            @PathVariable Long id){

        Preventa preventa = service.buscarPorId(id);

        return ResponseEntity.ok(preventa);
    }

    @PostMapping
    public ResponseEntity<Preventa> guardar(
            @RequestBody Preventa preventa){

        Preventa preventaGuardada = service.guardar(preventa);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(preventaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Preventa> actualizar(
            @PathVariable Long id,
            @RequestBody Preventa preventa){

        Preventa p = service.buscarPorId(id);

        if(p == null){
            return ResponseEntity.notFound().build();
        }

        p.setCliente(preventa.getCliente());
        p.setEventoId(preventa.getEventoId());
        p.setCantidadEntradas(preventa.getCantidadEntradas());
        p.setTotal(preventa.getTotal());
        p.setEstado(preventa.getEstado());

        Preventa actualizada = service.guardar(p);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id){

        service.eliminar(id);

        return ResponseEntity.ok(
                "Preventa eliminada correctamente"
        );
    }
}