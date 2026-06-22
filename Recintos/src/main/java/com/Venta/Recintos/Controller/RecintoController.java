package com.Venta.Recintos.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.Venta.Recintos.DTO.RecintoDTO;
import com.Venta.Recintos.Model.Recinto;
import com.Venta.Recintos.Service.RecintoService;

@RestController
@RequestMapping("/recinto")
public class RecintoController {

    private static final Logger log = LoggerFactory.getLogger(RecintoController.class);

    @Autowired
    private RecintoService service;

    @GetMapping
    public ResponseEntity<List<Recinto>> listar() {

        log.info("INICIO GET /recinto - Listando todos los recintos");

        List<Recinto> recintos = service.listar();

        log.info("FIN GET /recinto - Se encontraron {} recintos", recintos.size());

        return ResponseEntity.ok(recintos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recinto> buscarPorId(
            @PathVariable Long id) {

        log.info("INICIO GET /recinto/{} - Buscando recinto por ID", id);

        Recinto recinto = service.buscarPorId(id);

        if (recinto == null) {
            log.warn("FIN GET /recinto/{} - Recinto no encontrado", id);
            return ResponseEntity.notFound().build();
        }

        log.info("FIN GET /recinto/{} - Recinto encontrado correctamente", id);

        return ResponseEntity.ok(recinto);
    }

    @PostMapping
    public ResponseEntity<Recinto> guardar(
            @Valid @RequestBody RecintoDTO dto) {

        log.info("INICIO POST /recinto - Creando recinto: {}", dto.getNombre());

        Recinto recinto = new Recinto();

        recinto.setNombre(dto.getNombre());
        recinto.setDireccion(dto.getDireccion());
        recinto.setCiudad(dto.getCiudad());
        recinto.setCapacidad(dto.getCapacidad());
        recinto.setTipo(dto.getTipo());

        Recinto recintoGuardado = service.guardar(recinto);

        log.info("FIN POST /recinto - Recinto creado correctamente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recintoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recinto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RecintoDTO dto) {

        log.info("INICIO PUT /recinto/{} - Actualizando recinto", id);

        Recinto recinto = service.buscarPorId(id);

        if (recinto == null) {
            log.warn("FIN PUT /recinto/{} - Recinto no encontrado, no se pudo actualizar", id);
            return ResponseEntity.notFound().build();
        }

        recinto.setNombre(dto.getNombre());
        recinto.setDireccion(dto.getDireccion());
        recinto.setCiudad(dto.getCiudad());
        recinto.setCapacidad(dto.getCapacidad());
        recinto.setTipo(dto.getTipo());

        Recinto actualizado = service.guardar(recinto);

        log.info("FIN PUT /recinto/{} - Recinto actualizado correctamente", id);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        log.info("INICIO DELETE /recinto/{} - Eliminando recinto", id);

        Recinto recinto = service.buscarPorId(id);

        if (recinto == null) {
            log.warn("FIN DELETE /recinto/{} - Recinto no encontrado, no se pudo eliminar", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recinto no encontrado");
        }

        service.eliminar(id);

        log.info("FIN DELETE /recinto/{} - Recinto eliminado correctamente", id);

        return ResponseEntity.ok(
                "Recinto eliminado correctamente"
        );
    }
}