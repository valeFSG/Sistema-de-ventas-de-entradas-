package com.Venta.Validacion.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Venta.Validacion.DTO.DTO;
import com.Venta.Validacion.Model.Validacion;
import com.Venta.Validacion.Service.ValidacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/validaciones")
public class ValidacionController {

    private static final Logger log = LoggerFactory.getLogger(ValidacionController.class);

    @Autowired
    private ValidacionService validacionService;

    @PostMapping("/crear-validacion")
    public ResponseEntity<String> crearValidacion(@Valid @RequestBody DTO validacion) {

        log.info("INICIO POST /api/v1/validaciones/crear-validacion - Creando validacion");

        Boolean save = validacionService.guardarValidacion(validacion);

        if (save != true) {
            log.warn("FIN POST /api/v1/validaciones/crear-validacion - No se pudo crear la validacion");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear la validación.");
        }

        log.info("FIN POST /api/v1/validaciones/crear-validacion - Validacion creada correctamente");

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Validacion> listarValidaciones() {

        log.info("INICIO GET /api/v1/validaciones/listar - Listando validaciones");

        List<Validacion> validaciones = validacionService.listarValidaciones();

        log.info("FIN GET /api/v1/validaciones/listar - Se encontraron {} validaciones", validaciones.size());

        return validaciones;
    }

    @GetMapping("/buscar-ticket/{ticketId}")
    public List<Validacion> buscarPorTicket(@PathVariable Integer ticketId) {

        log.info("INICIO GET /api/v1/validaciones/buscar-ticket/{} - Buscando validaciones por ticket", ticketId);

        List<Validacion> validaciones = validacionService.obtenerPorTicket(ticketId);

        log.info("FIN GET /api/v1/validaciones/buscar-ticket/{} - Se encontraron {} validaciones", ticketId, validaciones.size());

        return validaciones;
    }

    @GetMapping("/buscar-estado/{estado}")
    public List<Validacion> buscarPorEstado(@PathVariable String estado) {

        log.info("INICIO GET /api/v1/validaciones/buscar-estado/{} - Buscando validaciones por estado", estado);

        List<Validacion> validaciones = validacionService.obtenerPorEstado(estado);

        log.info("FIN GET /api/v1/validaciones/buscar-estado/{} - Se encontraron {} validaciones", estado, validaciones.size());

        return validaciones;
    }

    @GetMapping("/buscar-codigo/{codigoEntrada}")
    public List<Validacion> buscarPorCodigoEntrada(@PathVariable String codigoEntrada) {

        log.info("INICIO GET /api/v1/validaciones/buscar-codigo/{} - Buscando validaciones por codigo de entrada", codigoEntrada);

        List<Validacion> validaciones = validacionService.obtenerPorCodigoEntrada(codigoEntrada);

        log.info("FIN GET /api/v1/validaciones/buscar-codigo/{} - Se encontraron {} validaciones", codigoEntrada, validaciones.size());

        return validaciones;
    }
}
