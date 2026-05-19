package com.venta.Preventa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.venta.Preventa.Model.Preventa;
import com.venta.Preventa.Service.PreventaService;

@RestController
@RequestMapping("/preventa")
public class PreventaController {

    @Autowired
    private PreventaService service;

    @GetMapping
    public List<Preventa> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Preventa buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public Preventa guardar(@RequestBody Preventa preventa){
        return service.guardar(preventa);
    }

    @PutMapping("/{id}")
    public Preventa actualizar(@PathVariable Long id,
                                @RequestBody Preventa preventa){

        Preventa p = service.buscarPorId(id);

        p.setCliente(preventa.getCliente());
        p.setEvento(preventa.getEvento());
        p.setCantidadEntradas(preventa.getCantidadEntradas());
        p.setTotal(preventa.getTotal());
        p.setEstado(preventa.getEstado());

        return service.guardar(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}