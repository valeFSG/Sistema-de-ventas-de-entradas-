package com.Venta.Recintos.Controller;

import java.util.List;

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

    @Autowired
    private RecintoService service;

    @GetMapping
    public ResponseEntity<List<Recinto>> listar() {

        List<Recinto> recintos = service.listar();

        return ResponseEntity.ok(recintos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recinto> buscarPorId(
            @PathVariable Long id) {

        Recinto recinto = service.buscarPorId(id);

        if (recinto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(recinto);
    }

    @PostMapping
    public ResponseEntity<Recinto> guardar(
            @Valid @RequestBody RecintoDTO dto) {

        Recinto recinto = new Recinto();

        recinto.setNombre(dto.getNombre());
        recinto.setDireccion(dto.getDireccion());
        recinto.setCiudad(dto.getCiudad());
        recinto.setCapacidad(dto.getCapacidad());
        recinto.setTipo(dto.getTipo());

        Recinto recintoGuardado = service.guardar(recinto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recintoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recinto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RecintoDTO dto) {

        Recinto recinto = service.buscarPorId(id);

        if (recinto == null) {
            return ResponseEntity.notFound().build();
        }

        recinto.setNombre(dto.getNombre());
        recinto.setDireccion(dto.getDireccion());
        recinto.setCiudad(dto.getCiudad());
        recinto.setCapacidad(dto.getCapacidad());
        recinto.setTipo(dto.getTipo());

        Recinto actualizado = service.guardar(recinto);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        Recinto recinto = service.buscarPorId(id);

        if (recinto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recinto no encontrado");
        }

        service.eliminar(id);

        return ResponseEntity.ok(
                "Recinto eliminado correctamente"
        );
    }
}

