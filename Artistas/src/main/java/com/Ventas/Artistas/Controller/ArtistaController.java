package com.Ventas.Artistas.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ventas.Artistas.DTO.DTO;
import com.Ventas.Artistas.Model.Artista;
import com.Ventas.Artistas.Service.ArtistaService;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {

    private static final Logger log = LoggerFactory.getLogger(ArtistaController.class);

    @Autowired
    private ArtistaService artistaService;

    @PostMapping("/crear-artista")
    public ResponseEntity<String> crearArtista(@RequestBody DTO artista) {

        log.info("INICIO POST /api/v1/artistas/crear-artista - Creando artista");

        Boolean save = artistaService.guardarArtista(artista);

        if (save != true) {
            log.warn("FIN POST /api/v1/artistas/crear-artista - No se pudo crear el artista");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el artista.");
        }

        log.info("FIN POST /api/v1/artistas/crear-artista - Artista creado correctamente");

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Artista> listarArtistas() {

        log.info("INICIO GET /api/v1/artistas/listar - Listando todos los artistas");

        List<Artista> artistas = artistaService.listarArtistas();

        log.info("FIN GET /api/v1/artistas/listar - Se encontraron {} artistas", artistas.size());

        return artistas;
    }

    @GetMapping("/buscar-especialidad/{especialidad}")
    public List<Artista> buscarPorEspecialidad(@PathVariable String especialidad) {

        log.info("INICIO GET /api/v1/artistas/buscar-especialidad/{} - Buscando artistas por especialidad", especialidad);

        List<Artista> artistas = artistaService.obtenerPorEspecialidad(especialidad);

        log.info("FIN GET /api/v1/artistas/buscar-especialidad/{} - Se encontraron {} artistas", especialidad, artistas.size());

        return artistas;
    }

    @GetMapping("/buscar-disponible/{disponible}")
    public List<Artista> buscarPorDisponibilidad(@PathVariable Boolean disponible) {

        log.info("INICIO GET /api/v1/artistas/buscar-disponible/{} - Buscando artistas por disponibilidad", disponible);

        List<Artista> artistas = artistaService.obtenerPorDisponibilidad(disponible);

        log.info("FIN GET /api/v1/artistas/buscar-disponible/{} - Se encontraron {} artistas", disponible, artistas.size());

        return artistas;
    }
}