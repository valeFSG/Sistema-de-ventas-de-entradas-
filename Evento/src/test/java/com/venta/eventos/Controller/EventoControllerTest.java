package com.venta.eventos.Controller;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.venta.eventos.Controller.EventoController;
import com.venta.eventos.Model.Evento;
import com.venta.eventos.Service.EventoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EventoService service;

    // =====================================================
    // GET /evento
    // =====================================================

    @Test
    @DisplayName("GET /evento -> Lista con datos")
    void listarConDatos() throws Exception {

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNombre("Concierto");

        when(service.listar()).thenReturn(List.of(evento));

        mockMvc.perform(get("/evento"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Concierto"));
    }

    @Test
    @DisplayName("GET /evento -> Lista vacía")
    void listarVacio() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/evento"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // =====================================================
    // GET /evento/{id}
    // =====================================================

    @Test
    @DisplayName("GET /evento/{id} -> Existe")
    void buscarPorIdExiste() throws Exception {

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNombre("Concierto");

        when(service.buscarPorId(1L)).thenReturn(evento);

        mockMvc.perform(get("/evento/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Concierto"));
    }

    @Test
    @DisplayName("GET /evento/{id} -> No existe")
    void buscarPorIdNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/evento/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // =====================================================
    // POST /evento
    // =====================================================

    @Test
    @DisplayName("POST /evento -> Datos válidos")
    void guardarValido() throws Exception {

        Evento evento = new Evento();
        evento.setId(1L);
        evento.setNombre("Concierto");

        when(service.guardar(any(Evento.class)))
                .thenReturn(evento);

        String json = """
        {
            "nombre":"Concierto",
            "categoria":"Musica",
            "fecha":"2030-12-31",
            "lugar":"Arena",
            "capacidad":1000,
            "recintoId":1
        }
        """;

        mockMvc.perform(post("/evento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("POST /evento -> Datos inválidos")
    void guardarInvalido() throws Exception {

        String json = """
        {
            "nombre":"",
            "categoria":"",
            "fecha":"2020-01-01",
            "lugar":"",
            "capacidad":0,
            "recintoId":null
        }
        """;

        mockMvc.perform(post("/evento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    // =====================================================
    // PUT /evento/{id}
    // =====================================================

    @Test
    @DisplayName("PUT /evento/{id} -> Existe")
    void actualizarExiste() throws Exception {

        Evento evento = new Evento();
        evento.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(evento);
        when(service.guardar(any(Evento.class))).thenReturn(evento);

        String json = """
        {
            "nombre":"Concierto",
            "categoria":"Musica",
            "fecha":"2030-12-31",
            "lugar":"Arena",
            "capacidad":1000,
            "recintoId":1
        }
        """;

        mockMvc.perform(put("/evento/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /evento/{id} -> No existe")
    void actualizarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        String json = """
        {
            "nombre":"Concierto",
            "categoria":"Musica",
            "fecha":"2030-12-31",
            "lugar":"Arena",
            "capacidad":1000,
            "recintoId":1
        }
        """;

        mockMvc.perform(put("/evento/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // =====================================================
    // DELETE /evento/{id}
    // =====================================================

    @Test
    @DisplayName("DELETE /evento/{id} -> Existe")
    void eliminarExiste() throws Exception {

        Evento evento = new Evento();
        evento.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(evento);

        mockMvc.perform(delete("/evento/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /evento/{id} -> No existe")
    void eliminarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/evento/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}


