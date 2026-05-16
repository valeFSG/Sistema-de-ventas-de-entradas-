package com.venta.ventas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venta.ventas.Model.Venta;
import com.venta.ventas.Service.VentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    // GET - listar
    @GetMapping
    public List<Venta> listar(){
        return service.listar();
    }

    // GET - buscar por id
    @GetMapping("/{id}")
    public Venta buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    // POST - guardar
    @PostMapping
    public Venta guardar(@RequestBody Venta venta){
        return service.guardar(venta);
    }

    // PUT - actualizar
    @PutMapping("/{id}")
    public Venta actualizar(@PathVariable Long id,
                            @RequestBody Venta venta){

        Venta v = service.buscarPorId(id);

        v.setCliente(venta.getCliente());
        v.setEvento(venta.getEvento());
        v.setCantidadEntradas(venta.getCantidadEntradas());
        v.setTotal(venta.getTotal());
        v.setMetodoPago(venta.getMetodoPago());

        return service.guardar(v);
    }

    // DELETE - eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}