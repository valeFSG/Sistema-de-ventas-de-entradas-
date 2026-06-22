package com.venta.Devoluciones.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venta.Devoluciones.Model.Devolucion;
import com.venta.Devoluciones.Service.DevolucionService;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {

    private static final Logger log = LoggerFactory.getLogger(DevolucionController.class);

    @Autowired
    private DevolucionService service;

    @GetMapping
    public ResponseEntity<List<Devolucion>> listar() {

        log.info("INICIO GET /devoluciones - Listando todas las devoluciones");

        List<Devolucion> devoluciones = service.listar();

        log.info("FIN GET /devoluciones - Se encontraron {} devoluciones", devoluciones.size());

        return ResponseEntity.ok(devoluciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarPorId(@PathVariable Long id) {

        log.info("INICIO GET /devoluciones/{} - Buscando devolucion por ID", id);

        Devolucion devolucion = service.buscarPorId(id);

        if (devolucion == null) {
            log.warn("FIN GET /devoluciones/{} - Devolucion no encontrada", id);
            return ResponseEntity.notFound().build();
        }

        log.info("FIN GET /devoluciones/{} - Devolucion encontrada correctamente", id);

        return ResponseEntity.ok(devolucion);
    }

    @PostMapping
    public ResponseEntity<Devolucion> guardar(@RequestBody Devolucion devolucion) {

        log.info("INICIO POST /devoluciones - Creando devolucion");

        Devolucion guardada = service.guardar(devolucion);

        if (guardada == null) {
            log.warn("FIN POST /devoluciones - No se pudo crear la devolucion");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.info("FIN POST /devoluciones - Devolucion creada correctamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizar(
            @PathVariable Long id,
            @RequestBody Devolucion devolucion) {

        log.info("INICIO PUT /devoluciones/{} - Actualizando devolucion", id);

        Devolucion d = service.buscarPorId(id);

        if (d == null) {
            log.warn("FIN PUT /devoluciones/{} - Devolucion no encontrada, no se pudo actualizar", id);
            return ResponseEntity.notFound().build();
        }

        d.setCliente(devolucion.getCliente());
        d.setEvento(devolucion.getEvento());
        d.setMotivo(devolucion.getMotivo());
        d.setMonto(devolucion.getMonto());
        d.setEstado(devolucion.getEstado());

        Devolucion actualizada = service.guardar(d);

        log.info("FIN PUT /devoluciones/{} - Devolucion actualizada correctamente", id);

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        log.info("INICIO DELETE /devoluciones/{} - Eliminando devolucion", id);

        Devolucion devolucion = service.buscarPorId(id);

        if (devolucion == null) {
            log.warn("FIN DELETE /devoluciones/{} - Devolucion no encontrada, no se pudo eliminar", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Devolución no encontrada");
        }

        service.eliminar(id);

        log.info("FIN DELETE /devoluciones/{} - Devolucion eliminada correctamente", id);

        return ResponseEntity.ok("Devolución eliminada correctamente");
    }
}