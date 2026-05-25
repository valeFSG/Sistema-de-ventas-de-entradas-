package com.Venta.Recintos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Venta.Recintos.Model.Recinto;
import com.Venta.Recintos.Service.RecintoService;

@RestController
@RequestMapping("/recinto")
public class RecintoController {

    @Autowired
    private RecintoService service;

    @GetMapping
    public ResponseEntity<List<Recinto>> listar(){

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recinto> buscarPorId(@PathVariable Long id){

        Recinto recinto = service.buscarPorId(id);

        return ResponseEntity.ok(recinto);
    }

    @PostMapping
    public ResponseEntity<Recinto> guardar(@RequestBody Recinto recinto){

        Recinto recintoGuardado = service.guardar(recinto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recintoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recinto> actualizar(@PathVariable Long id,
                                              @RequestBody Recinto recinto){

        Recinto r = service.buscarPorId(id);

        r.setNombre(recinto.getNombre());
        r.setDireccion(recinto.getDireccion());
        r.setCiudad(recinto.getCiudad());
        r.setCapacidad(recinto.getCapacidad());
        r.setTipo(recinto.getTipo());

        Recinto actualizado = service.guardar(r);

        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        service.eliminar(id);

        return ResponseEntity.ok("Recinto eliminado correctamente");
    }
}