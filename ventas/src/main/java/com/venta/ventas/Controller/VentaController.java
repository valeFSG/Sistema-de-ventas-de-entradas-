package com.venta.ventas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id){

        Venta venta = service.buscarPorId(id);

        return ResponseEntity.ok(venta);
    }

    // POST - guardar
    @PostMapping
    public ResponseEntity<Venta> guardar(@RequestBody Venta venta){

        Venta ventaGuardada = service.guardar(venta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaGuardada);
    }

    // PUT - actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @RequestBody Venta venta){

        Venta v = service.buscarPorId(id);

        if(v == null){
            return ResponseEntity.notFound().build();
        }

        v.setCliente(venta.getCliente());
        v.setEventoId(venta.getEventoId());
        v.setCantidadEntradas(venta.getCantidadEntradas());
        v.setTotal(venta.getTotal());
        v.setMetodoPago(venta.getMetodoPago());

        Venta actualizada = service.guardar(v);

        return ResponseEntity.ok(actualizada);
    }

    // DELETE - eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        service.eliminar(id);

        return ResponseEntity.ok(
                "Venta eliminada correctamente"
        );
    }
}