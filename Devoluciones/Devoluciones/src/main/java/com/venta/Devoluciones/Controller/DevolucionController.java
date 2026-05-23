package com.venta.Devoluciones.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.venta.Devoluciones.Model.Devolucion;
import com.venta.Devoluciones.Service.DevolucionService;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {

    @Autowired
    private DevolucionService service;

    @GetMapping
    public List<Devolucion> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Devolucion buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public Devolucion guardar(@RequestBody Devolucion devolucion){
        return service.guardar(devolucion);
    }

    @PutMapping("/{id}")
    public Devolucion actualizar(@PathVariable Long id,
                                 @RequestBody Devolucion devolucion){

        Devolucion d = service.buscarPorId(id);

        d.setCliente(devolucion.getCliente());
        d.setEvento(devolucion.getEvento());
        d.setMotivo(devolucion.getMotivo());
        d.setMonto(devolucion.getMonto());
        d.setEstado(devolucion.getEstado());

        return service.guardar(d);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}