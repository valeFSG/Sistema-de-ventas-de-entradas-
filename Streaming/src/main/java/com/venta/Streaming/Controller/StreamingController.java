package com.venta.Streaming.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(StreamingController.class);

    @Autowired
    private StreamingService streamingService;

    @PostMapping("/crear-streaming")
    public ResponseEntity<String> crearStreaming(@Valid @RequestBody StreamingDTO streamingDTO) {

        log.info("INICIO POST /api/v1/streaming/crear-streaming - Creando acceso streaming");

        Boolean save = streamingService.guardarStreaming(streamingDTO);

        if (save != true) {
            log.warn("FIN POST /api/v1/streaming/crear-streaming - No se pudo crear el acceso streaming");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se pudo crear el acceso streaming.");
        }

        log.info("FIN POST /api/v1/streaming/crear-streaming - Acceso streaming creado correctamente");

        return ResponseEntity.ok("creado correctamente");
    }

    @GetMapping("/listar")
    public List<Streaming> listarStreaming() {

        log.info("INICIO GET /api/v1/streaming/listar - Listando accesos streaming");

        List<Streaming> streamings = streamingService.listarStreaming();

        log.info("FIN GET /api/v1/streaming/listar - Se encontraron {} accesos streaming", streamings.size());

        return streamings;
    }

    @GetMapping("/buscar-evento/{eventoId}")
    public List<Streaming> buscarPorEvento(@PathVariable Integer eventoId) {

        log.info("INICIO GET /api/v1/streaming/buscar-evento/{} - Buscando streaming por evento", eventoId);

        List<Streaming> streamings = streamingService.obtenerPorEvento(eventoId);

        log.info("FIN GET /api/v1/streaming/buscar-evento/{} - Se encontraron {} accesos streaming", eventoId, streamings.size());

        return streamings;
    }

    @GetMapping("/buscar-activo/{activo}")
    public List<Streaming> buscarPorActivo(@PathVariable Boolean activo) {

        log.info("INICIO GET /api/v1/streaming/buscar-activo/{} - Buscando streaming por estado activo", activo);

        List<Streaming> streamings = streamingService.obtenerPorActivo(activo);

        log.info("FIN GET /api/v1/streaming/buscar-activo/{} - Se encontraron {} accesos streaming", activo, streamings.size());

        return streamings;
    }

    @GetMapping("/buscar-codigo/{codigoAcceso}")
    public List<Streaming> buscarPorCodigo(@PathVariable String codigoAcceso) {

        log.info("INICIO GET /api/v1/streaming/buscar-codigo/{} - Buscando streaming por código de acceso", codigoAcceso);

        List<Streaming> streamings = streamingService.obtenerPorCodigoAcceso(codigoAcceso);

        log.info("FIN GET /api/v1/streaming/buscar-codigo/{} - Se encontraron {} accesos streaming", codigoAcceso, streamings.size());

        return streamings;
    }
}