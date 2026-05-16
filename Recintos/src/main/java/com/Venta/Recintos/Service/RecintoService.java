package com.Venta.Recintos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Venta.Recintos.Model.Recinto;
import com.Venta.Recintos.Repository.RecintoRepository;
@Service
public class RecintoService {

    @Autowired
    private RecintoRepository repository;

    public List<Recinto> listar(){
        return repository.findAll();
    }

    public Recinto guardar(Recinto recinto){
        return repository.save(recinto);
    }

    public Recinto buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id){
        repository.deleteById(id);
    }
}