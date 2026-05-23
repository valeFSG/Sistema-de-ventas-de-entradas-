package com.Venta.Promotores.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Venta.Promotores.DTO.PromotorDTO;
import com.Venta.Promotores.Model.Promotor;
import com.Venta.Promotores.Repository.PromotorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PromotorService {

    @Autowired
    private PromotorRepository promotorRepository;

    public Boolean guardarPromotor(PromotorDTO promotorDTO) {

        Promotor promotor = new Promotor();

        promotor.setNombre(promotorDTO.getNombre());
        promotor.setCorreo(promotorDTO.getCorreo());
        promotor.setTelefono(promotorDTO.getTelefono());
        promotor.setComision(promotorDTO.getComision());
        promotor.setEstado(promotorDTO.getEstado());

        promotorRepository.save(promotor);

        return true;
    }

    public List<Promotor> listarPromotores() {
        return promotorRepository.findAll();
    }

    public List<Promotor> obtenerPorEstado(String estado) {
        return promotorRepository.findByEstado(estado);
    }

    public List<Promotor> obtenerPorCorreo(String correo) {
        return promotorRepository.findByCorreo(correo);
    }
}