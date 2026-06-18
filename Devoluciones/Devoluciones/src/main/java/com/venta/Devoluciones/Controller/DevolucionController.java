package com.venta.Devoluciones.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venta.Devoluciones.Model.Devolucion;
import com.venta.Devoluciones.Service.DevolucionService;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService service;

    @GetMapping
    public ResponseEntity<List<Devolucion>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarPorId(@PathVariable Long id) {

        Devolucion devolucion = service.buscarPorId(id);

        if (devolucion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(devolucion);
    }

    @PostMapping
    public ResponseEntity<Devolucion> guardar(@RequestBody Devolucion devolucion) {

        Devolucion guardada = service.guardar(devolucion);

        if (guardada == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizar(
            @PathVariable Long id,
            @RequestBody Devolucion devolucion) {

        Devolucion d = service.buscarPorId(id);

        if (d == null) {
            return ResponseEntity.notFound().build();
        }

        d.setCliente(devolucion.getCliente());
        d.setEvento(devolucion.getEvento());
        d.setMotivo(devolucion.getMotivo());
        d.setMonto(devolucion.getMonto());
        d.setEstado(devolucion.getEstado());

        return ResponseEntity.ok(service.guardar(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        Devolucion devolucion = service.buscarPorId(id);

        if (devolucion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Devolución no encontrada");
        }

        service.eliminar(id);

        return ResponseEntity.ok("Devolución eliminada correctamente");
    }
}