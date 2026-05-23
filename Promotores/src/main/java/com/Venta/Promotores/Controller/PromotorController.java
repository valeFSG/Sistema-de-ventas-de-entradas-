package com.Venta.Promotores.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Venta.Promotores.DTO.PromotorDTO;
import com.Venta.Promotores.Model.Promotor;
import com.Venta.Promotores.Service.PromotorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/promotores")
public class PromotorController {

    @Autowired
    private PromotorService promotorService;

    @PostMapping("/crear-promotor")
    public ResponseEntity<String> crearPromotor(@Valid @RequestBody PromotorDTO promotorDTO) {

        Boolean save = promotorService.guardarPromotor(promotorDTO);

        if (save != true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el promotor.");
        }

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Promotor> listarPromotores() {
        return promotorService.listarPromotores();
    }

    @GetMapping("/buscar-estado/{estado}")
    public List<Promotor> buscarPorEstado(@PathVariable String estado) {
        return promotorService.obtenerPorEstado(estado);
    }

    @GetMapping("/buscar-correo/{correo}")
    public List<Promotor> buscarPorCorreo(@PathVariable String correo) {
        return promotorService.obtenerPorCorreo(correo);
    }
}