package com.Venta.Promotores.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(PromotorController.class);

    @Autowired
    private PromotorService promotorService;

    @PostMapping("/crear-promotor")
    public ResponseEntity<String> crearPromotor(@Valid @RequestBody PromotorDTO promotorDTO) {

        log.info("INICIO POST /api/v1/promotores/crear-promotor - Creando promotor");

        Boolean save = promotorService.guardarPromotor(promotorDTO);

        if (save != true) {
            log.warn("FIN POST /api/v1/promotores/crear-promotor - No se pudo crear el promotor");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el promotor.");
        }

        log.info("FIN POST /api/v1/promotores/crear-promotor - Promotor creado correctamente");

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Promotor> listarPromotores() {

        log.info("INICIO GET /api/v1/promotores/listar - Listando todos los promotores");

        List<Promotor> promotores = promotorService.listarPromotores();

        log.info("FIN GET /api/v1/promotores/listar - Se encontraron {} promotores", promotores.size());

        return promotores;
    }

    @GetMapping("/buscar-estado/{estado}")
    public List<Promotor> buscarPorEstado(@PathVariable String estado) {

        log.info("INICIO GET /api/v1/promotores/buscar-estado/{} - Buscando promotores por estado", estado);

        List<Promotor> promotores = promotorService.obtenerPorEstado(estado);

        log.info("FIN GET /api/v1/promotores/buscar-estado/{} - Se encontraron {} promotores", estado, promotores.size());

        return promotores;
    }

    @GetMapping("/buscar-correo/{correo}")
    public List<Promotor> buscarPorCorreo(@PathVariable String correo) {

        log.info("INICIO GET /api/v1/promotores/buscar-correo/{} - Buscando promotores por correo", correo);

        List<Promotor> promotores = promotorService.obtenerPorCorreo(correo);

        log.info("FIN GET /api/v1/promotores/buscar-correo/{} - Se encontraron {} promotores", correo, promotores.size());

        return promotores;
    }
}