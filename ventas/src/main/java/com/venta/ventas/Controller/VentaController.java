package com.venta.ventas.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(VentaController.class);

    @Autowired
    private VentaService service;

    
    @GetMapping
    public ResponseEntity<List<Venta>> listar() {

        log.info("INICIO GET /ventas - Listando todas las ventas");

        List<Venta> ventas = service.listar();

        log.info("FIN GET /ventas - Se encontraron {} ventas", ventas.size());

        return ResponseEntity.ok(ventas);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {

        log.info("INICIO GET /ventas/{} - Buscando venta por ID", id);

        Venta venta = service.buscarPorId(id);

        if (venta == null) {
            log.warn("FIN GET /ventas/{} - Venta no encontrada", id);
            return ResponseEntity.notFound().build();
        }

        log.info("FIN GET /ventas/{} - Venta encontrada correctamente", id);

        return ResponseEntity.ok(venta);
    }

   
    @PostMapping
    public ResponseEntity<Venta> guardar(
            @Valid @RequestBody VentaDTO dto) {

        log.info("INICIO POST /ventas - Creando venta para cliente: {}", dto.getCliente());

        Venta venta = new Venta();

        venta.setCliente(dto.getCliente());
        venta.setEvento(dto.getEvento());
        venta.setCantidadEntradas(dto.getCantidadEntradas());
        venta.setTotal(dto.getTotal());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setEventoId(dto.getEventoId());

        Venta ventaGuardada = service.guardar(venta);

        log.info("FIN POST /ventas - Venta creada correctamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VentaDTO dto) {

        log.info("INICIO PUT /ventas/{} - Actualizando venta", id);

        Venta venta = service.buscarPorId(id);

        if (venta == null) {
            log.warn("FIN PUT /ventas/{} - Venta no encontrada, no se pudo actualizar", id);
            return ResponseEntity.notFound().build();
        }

        venta.setCliente(dto.getCliente());
        venta.setEvento(dto.getEvento());
        venta.setCantidadEntradas(dto.getCantidadEntradas());
        venta.setTotal(dto.getTotal());
        venta.setMetodoPago(dto.getMetodoPago());
        venta.setEventoId(dto.getEventoId());

        Venta actualizada = service.guardar(venta);

        log.info("FIN PUT /ventas/{} - Venta actualizada correctamente", id);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        log.info("INICIO DELETE /ventas/{} - Eliminando venta", id);

        Venta venta = service.buscarPorId(id);

        if (venta == null) {
            log.warn("FIN DELETE /ventas/{} - Venta no encontrada, no se pudo eliminar", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Venta no encontrada");
        }

        service.eliminar(id);

        log.info("FIN DELETE /ventas/{} - Venta eliminada correctamente", id);

        return ResponseEntity.ok("Venta eliminada correctamente");
    }
}