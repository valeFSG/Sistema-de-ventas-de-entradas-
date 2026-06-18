package com.venta.Preventa.Controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.venta.Preventa.DTO.PreventaDTO;
import com.venta.Preventa.Model.Preventa;
import com.venta.Preventa.Service.PreventaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PreventaController.class)
public class PreventaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PreventaService service;

    @Test
    @DisplayName("GET /preventa -> Lista con datos")
    void listarConDatos() throws Exception {

        Preventa preventa = new Preventa();
        preventa.setId(1L);
        preventa.setCliente("Valentina");

        when(service.listar()).thenReturn(List.of(preventa));

        mockMvc.perform(get("/preventa"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /preventa -> Lista vacía")
    void listarVacio() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/preventa"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /preventa/{id} -> Existe")
    void buscarPorIdExiste() throws Exception {

        Preventa preventa = new Preventa();
        preventa.setId(1L);
        preventa.setCliente("Valentina");

        when(service.buscarPorId(1L)).thenReturn(preventa);

        mockMvc.perform(get("/preventa/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /preventa/{id} -> No existe")
    void buscarPorIdNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/preventa/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /preventa -> Datos válidos")
    void guardarValido() throws Exception {

        Preventa preventa = new Preventa();
        preventa.setId(1L);
        preventa.setCliente("Valentina");

        when(service.guardar(any(Preventa.class)))
                .thenReturn(preventa);

        String json = """
        {
            "cliente":"Valentina",
            "eventoId":1,
            "cantidadEntradas":2,
            "total":50000,
            "estado":"ACTIVA"
        }
        """;

        mockMvc.perform(post("/preventa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /preventa -> Datos inválidos")
    void guardarInvalido() throws Exception {

        String json = """
        {
            "cliente":"",
            "eventoId":null,
            "cantidadEntradas":0,
            "total":0,
            "estado":""
        }
        """;

        mockMvc.perform(post("/preventa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /preventa/{id} -> Existe")
    void actualizarExiste() throws Exception {

        Preventa preventa = new Preventa();
        preventa.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(preventa);
        when(service.guardar(any(Preventa.class))).thenReturn(preventa);

        String json = """
        {
            "cliente":"Valentina",
            "eventoId":1,
            "cantidadEntradas":3,
            "total":70000,
            "estado":"ACTIVA"
        }
        """;

        mockMvc.perform(put("/preventa/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /preventa/{id} -> No existe")
    void actualizarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        String json = """
        {
            "cliente":"Valentina",
            "eventoId":1,
            "cantidadEntradas":3,
            "total":70000,
            "estado":"ACTIVA"
        }
        """;

        mockMvc.perform(put("/preventa/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /preventa/{id} -> Existe")
    void eliminarExiste() throws Exception {

        Preventa preventa = new Preventa();
        preventa.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(preventa);

        mockMvc.perform(delete("/preventa/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /preventa/{id} -> No existe")
    void eliminarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/preventa/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
