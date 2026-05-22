package com.Ventas.Artistas.Controller;

import java.util.List;

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

    @Autowired
    private ArtistaService artistaService;

    @PostMapping("/crear-artista")
    public ResponseEntity<String> crearArtista(@RequestBody DTO artista) {

        Boolean save = artistaService.guardarArtista(artista);

        if (save != true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el artista.");
        }

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Artista> listarArtistas() {
        return artistaService.listarArtistas();
    }

    @GetMapping("/buscar-especialidad/{especialidad}")
    public List<Artista> buscarPorEspecialidad(@PathVariable String especialidad) {
        return artistaService.obtenerPorEspecialidad(especialidad);
    }

    @GetMapping("/buscar-disponible/{disponible}")
    public List<Artista> buscarPorDisponibilidad(@PathVariable Boolean disponible) {
        return artistaService.obtenerPorDisponibilidad(disponible);
    }
}