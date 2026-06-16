package com.venta.ventas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venta.ventas.DTO.VentaDTO;
import com.venta.ventas.Model.Venta;
import com.venta.ventas.Service.VentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    
    @GetMapping
    public ResponseEntity<List<Venta>> listar() {

        List<Venta> ventas = service.listar();

        return ResponseEntity.ok(ventas);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {

        Venta venta = service.buscarPorId(id);

        if (venta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(venta);
    }

   
    @PostMapping
    public ResponseEntity<Venta> guardar(
            @Valid @RequestBody VentaDTO dto) {

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

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VentaDTO dto) {

        Venta venta = service.buscarPorId(id);

        if (venta == null) {
            return ResponseEntity.notFound().build();
        }

        venta.setCliente(dto.getCliente());
        venta.setEvento(dto.getEvento());
        venta.setCantidadEntradas(dto.getCantidadEntradas());
        venta.setTotal(dto.getTotal());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setEventoId(dto.getEventoId());

        Venta actualizada = service.guardar(venta);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Venta venta = service.buscarPorId(id);

        if (venta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Venta no encontrada");
        }

        service.eliminar(id);

        return ResponseEntity.ok("Venta eliminada correctamente");
    }
}