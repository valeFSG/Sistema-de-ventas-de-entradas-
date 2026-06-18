package com.venta.Streaming.Controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.venta.Streaming.DTO.StreamingDTO;
import com.venta.Streaming.Model.Streaming;
import com.venta.Streaming.Service.StreamingService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StreamingController.class)
public class StreamingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StreamingService streamingService;

    @Test
    @DisplayName("GET /listar -> Lista con datos")
    void listarConDatos() throws Exception {

        Streaming streaming = new Streaming();
        streaming.setId(1);
        streaming.setCodigoAcceso("ABC123");

        when(streamingService.listarStreaming())
                .thenReturn(List.of(streaming));

        mockMvc.perform(get("/api/v1/streaming/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /listar -> Lista vacía")
    void listarVacio() throws Exception {

        when(streamingService.listarStreaming())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/streaming/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST -> Crear streaming exitoso")
    void crearStreamingExitoso() throws Exception {

        when(streamingService.guardarStreaming(any(StreamingDTO.class)))
                .thenReturn(true);

        String json = """
        {
            "eventoId":1,
            "plataforma":"YouTube",
            "urlAcceso":"https://youtube.com/live",
            "codigoAcceso":"ABC123",
            "fechaInicio":"2030-01-01T10:00:00",
            "fechaFin":"2030-01-01T12:00:00",
            "activo":true
        }
        """;

        mockMvc.perform(post("/api/v1/streaming/crear-streaming")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST -> Crear streaming exitoso 2")
    void crearStreamingExitoso2() throws Exception {

        when(streamingService.guardarStreaming(any(StreamingDTO.class)))
                .thenReturn(true);

        String json = """
        {
            "eventoId":2,
            "plataforma":"Twitch",
            "urlAcceso":"https://twitch.tv/evento",
            "codigoAcceso":"XYZ999",
            "fechaInicio":"2030-02-01T10:00:00",
            "fechaFin":"2030-02-01T12:00:00",
            "activo":false
        }
        """;

        mockMvc.perform(post("/api/v1/streaming/crear-streaming")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST -> Error al crear")
    void crearStreamingError() throws Exception {

        when(streamingService.guardarStreaming(any(StreamingDTO.class)))
                .thenReturn(false);

        String json = """
        {
            "eventoId":1,
            "plataforma":"YouTube",
            "urlAcceso":"https://youtube.com/live",
            "codigoAcceso":"ABC123",
            "fechaInicio":"2030-01-01T10:00:00",
            "fechaFin":"2030-01-01T12:00:00",
            "activo":true
        }
        """;

        mockMvc.perform(post("/api/v1/streaming/crear-streaming")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET evento con datos")
    void eventoConDatos() throws Exception {

        Streaming streaming = new Streaming();
        streaming.setId(1);

        when(streamingService.obtenerPorEvento(1))
                .thenReturn(List.of(streaming));

        mockMvc.perform(get("/api/v1/streaming/buscar-evento/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET evento vacío")
    void eventoVacio() throws Exception {

        when(streamingService.obtenerPorEvento(99))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/streaming/buscar-evento/99"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET activo true")
    void activoTrue() throws Exception {

        Streaming streaming = new Streaming();
        streaming.setId(1);

        when(streamingService.obtenerPorActivo(true))
                .thenReturn(List.of(streaming));

        mockMvc.perform(get("/api/v1/streaming/buscar-activo/true"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET activo false")
    void activoFalse() throws Exception {

        when(streamingService.obtenerPorActivo(false))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/streaming/buscar-activo/false"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET código con datos")
    void codigoConDatos() throws Exception {

        Streaming streaming = new Streaming();
        streaming.setId(1);

        when(streamingService.obtenerPorCodigoAcceso("ABC123"))
                .thenReturn(List.of(streaming));

        mockMvc.perform(get("/api/v1/streaming/buscar-codigo/ABC123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET código vacío")
    void codigoVacio() throws Exception {

        when(streamingService.obtenerPorCodigoAcceso("XXX"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/streaming/buscar-codigo/XXX"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
