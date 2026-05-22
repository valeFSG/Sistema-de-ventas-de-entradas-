package com.venta.Preventa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venta.Preventa.Model.Preventa;
import com.venta.Preventa.Repository.PreventaRepository;

@Service
public class PreventaService {

    @Autowired
    private PreventaRepository repository;

    public List<Preventa> listar(){
        return repository.findAll();
    }

    public Preventa guardar(Preventa preventa){
        return repository.save(preventa);
    }

    public Preventa buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}