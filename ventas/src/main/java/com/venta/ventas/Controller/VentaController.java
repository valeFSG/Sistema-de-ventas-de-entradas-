package com.venta.ventas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.venta.ventas.DTO.VentaDTO;
import com.venta.ventas.Model.Venta;
import com.venta.ventas.Service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    // GET - listar
    @GetMapping
    public ResponseEntity<List<Venta>> listar(){

        return ResponseEntity.ok(service.listar());
    }

    // GET - buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(
            @PathVariable Long id){

        Venta venta = service.buscarPorId(id);

        return ResponseEntity.ok(venta);
    }

    // POST - guardar
    @PostMapping
    public ResponseEntity<Venta> guardar(
            @Valid @RequestBody VentaDTO dto){

        Venta venta = new Venta();

        venta.setCliente(dto.getCliente());
        venta.setEvento(dto.getEvento());
        venta.setCantidadEntradas(dto.getCantidadEntradas());
        venta.setTotal(dto.getTotal());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setEventoId(dto.getEventoId());

        Venta ventaGuardada = service.guardar(venta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaGuardada);
    }

    // PUT - actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VentaDTO dto){

        Venta v = service.buscarPorId(id);

        if(v == null){
            return ResponseEntity.notFound().build();
        }

        v.setCliente(dto.getCliente());
        v.setEvento(dto.getEvento());
        v.setCantidadEntradas(dto.getCantidadEntradas());
        v.setTotal(dto.getTotal());
        v.setMetodoPago(dto.getMetodoPago());
        v.setEventoId(dto.getEventoId());

        Venta actualizada = service.guardar(v);

        return ResponseEntity.ok(actualizada);
    }

    // DELETE - eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id){

        service.eliminar(id);

        return ResponseEntity.ok(
                "Venta eliminada correctamente"
        );
    }
}