package com.Venta.Validacion.Controller;

import java.util.List;

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

    @Autowired
    private ValidacionService validacionService;

    @PostMapping("/crear-validacion")
    public ResponseEntity<String> crearValidacion(@Valid @RequestBody DTO validacion) {

        Boolean save = validacionService.guardarValidacion(validacion);

        if (save != true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear la validación.");
        }

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Validacion> listarValidaciones() {
        return validacionService.listarValidaciones();
    }

    @GetMapping("/buscar-ticket/{ticketId}")
    public List<Validacion> buscarPorTicket(@PathVariable Integer ticketId) {
        return validacionService.obtenerPorTicket(ticketId);
    }

    @GetMapping("/buscar-estado/{estado}")
    public List<Validacion> buscarPorEstado(@PathVariable String estado) {
        return validacionService.obtenerPorEstado(estado);
    }

    @GetMapping("/buscar-codigo/{codigoEntrada}")
    public List<Validacion> buscarPorCodigoEntrada(@PathVariable String codigoEntrada) {
        return validacionService.obtenerPorCodigoEntrada(codigoEntrada);
    }
}