package com.venta.Streaming.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.venta.Streaming.DTO.StreamingDTO;
import com.venta.Streaming.Model.Streaming;
import com.venta.Streaming.Service.StreamingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/streaming")
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @PostMapping("/crear-streaming")
    public ResponseEntity<String> crearStreaming(@Valid @RequestBody StreamingDTO streamingDTO) {

        Boolean save = streamingService.guardarStreaming(streamingDTO);

        if (save != true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el acceso streaming.");
        }

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Streaming> listarStreaming() {
        return streamingService.listarStreaming();
    }

    @GetMapping("/buscar-evento/{eventoId}")
    public List<Streaming> buscarPorEvento(@PathVariable Integer eventoId) {
        return streamingService.obtenerPorEvento(eventoId);
    }

    @GetMapping("/buscar-activo/{activo}")
    public List<Streaming> buscarPorActivo(@PathVariable Boolean activo) {
        return streamingService.obtenerPorActivo(activo);
    }

    @GetMapping("/buscar-codigo/{codigoAcceso}")
    public List<Streaming> buscarPorCodigo(@PathVariable String codigoAcceso) {
        return streamingService.obtenerPorCodigoAcceso(codigoAcceso);
    }
}
