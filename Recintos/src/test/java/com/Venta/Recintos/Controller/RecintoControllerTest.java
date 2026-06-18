package com.Venta.Recintos.Controller;

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

import com.Venta.Recintos.Model.Recinto;
import com.Venta.Recintos.Service.RecintoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecintoController.class)
public class RecintoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecintoService service;

    @Test
    @DisplayName("GET /recinto -> Lista con datos")
    void listarConDatos() throws Exception {

        Recinto recinto = new Recinto();
        recinto.setId(1L);
        recinto.setNombre("Arena");

        when(service.listar()).thenReturn(List.of(recinto));

        mockMvc.perform(get("/recinto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Arena"));
    }

    @Test
    @DisplayName("GET /recinto -> Lista vacía")
    void listarVacio() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/recinto"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /recinto/{id} -> Existe")
    void buscarPorIdExiste() throws Exception {

        Recinto recinto = new Recinto();
        recinto.setId(1L);
        recinto.setNombre("Arena");

        when(service.buscarPorId(1L)).thenReturn(recinto);

        mockMvc.perform(get("/recinto/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Arena"));
    }

    @Test
    @DisplayName("GET /recinto/{id} -> No existe")
    void buscarPorIdNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/recinto/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /recinto -> Datos válidos")
    void guardarValido() throws Exception {

        Recinto recinto = new Recinto();
        recinto.setId(1L);
        recinto.setNombre("Arena");

        when(service.guardar(any(Recinto.class)))
                .thenReturn(recinto);

        String json = """
        {
            "nombre":"Arena",
            "direccion":"Av. Principal",
            "ciudad":"Puerto Montt",
            "capacidad":1000,
            "tipo":"Estadio"
        }
        """;

        mockMvc.perform(post("/recinto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("POST /recinto -> Datos inválidos")
    void guardarInvalido() throws Exception {

        String json = """
        {
            "nombre":"",
            "direccion":"",
            "ciudad":"",
            "capacidad":0,
            "tipo":""
        }
        """;

        mockMvc.perform(post("/recinto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /recinto/{id} -> Existe")
    void actualizarExiste() throws Exception {

        Recinto recinto = new Recinto();
        recinto.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(recinto);
        when(service.guardar(any(Recinto.class))).thenReturn(recinto);

        String json = """
        {
            "nombre":"Arena",
            "direccion":"Av. Principal",
            "ciudad":"Puerto Montt",
            "capacidad":1000,
            "tipo":"Estadio"
        }
        """;

        mockMvc.perform(put("/recinto/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /recinto/{id} -> No existe")
    void actualizarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        String json = """
        {
            "nombre":"Arena",
            "direccion":"Av. Principal",
            "ciudad":"Puerto Montt",
            "capacidad":1000,
            "tipo":"Estadio"
        }
        """;

        mockMvc.perform(put("/recinto/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /recinto/{id} -> Existe")
    void eliminarExiste() throws Exception {

        Recinto recinto = new Recinto();
        recinto.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(recinto);

        mockMvc.perform(delete("/recinto/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /recinto/{id} -> No existe")
    void eliminarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/recinto/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

