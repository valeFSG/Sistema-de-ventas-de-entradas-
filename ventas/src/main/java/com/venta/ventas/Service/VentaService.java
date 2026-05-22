package com.venta.ventas.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venta.ventas.Model.Venta;
import com.venta.ventas.Repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repository;

    public List<Venta> listar(){
        return repository.findAll();
    }

    public Venta guardar(Venta venta){
        return repository.save(venta);
    }

    public Venta buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}