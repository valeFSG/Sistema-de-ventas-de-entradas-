package com.Venta.Recintos.Controller;

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

import com.Venta.Recintos.Model.Recinto;
import com.Venta.Recintos.Service.RecintoService;

@RestController
@RequestMapping("/recinto")
public class RecintoController {

    @Autowired
    private RecintoService service;

    @GetMapping
    public List<Recinto> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Recinto buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    public Recinto guardar(@RequestBody Recinto recinto){
        return service.guardar(recinto);
    }

    @PutMapping("/{id}")
    public Recinto actualizar(@PathVariable Long id,
                              @RequestBody Recinto recinto){

        Recinto r = service.buscarPorId(id);

        r.setNombre(recinto.getNombre());
        r.setDireccion(recinto.getDireccion());
        r.setCiudad(recinto.getCiudad());
        r.setCapacidad(recinto.getCapacidad());
        r.setTipo(recinto.getTipo());

        return service.guardar(r);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        service.eliminar(id);
    }
}