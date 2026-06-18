package com.Ventas.Artistas.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Ventas.Artistas.DTO.DTO;
import com.Ventas.Artistas.Model.Artista;
import com.Ventas.Artistas.Service.ArtistaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistaController.class)
public class ArtistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ArtistaService service;

    @Test
    @DisplayName("POST crear artista - exitoso")
    void crearArtistaExitoso() throws Exception {

        when(service.guardarArtista(org.mockito.ArgumentMatchers.any(DTO.class)))
                .thenReturn(true);

        String json = """
        {
            "nombreArtistico":"Shakira",
            "especialidad":"Cantante",
            "biografia":"Artista internacional",
            "fechaDisponible":"2030-01-01T10:00:00",
            "disponible":true
        }
        """;

        mockMvc.perform(post("/api/v1/artistas/crear-artista")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST crear artista - error")
    void crearArtistaError() throws Exception {

        when(service.guardarArtista(org.mockito.ArgumentMatchers.any(DTO.class)))
                .thenReturn(false);

        String json = """
        {
            "nombreArtistico":"Shakira",
            "especialidad":"Cantante",
            "biografia":"Artista internacional",
            "fechaDisponible":"2030-01-01T10:00:00",
            "disponible":true
        }
        """;

        mockMvc.perform(post("/api/v1/artistas/crear-artista")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST crear artista - segundo caso")
    void crearArtistaExitoso2() throws Exception {

        when(service.guardarArtista(org.mockito.ArgumentMatchers.any(DTO.class)))
                .thenReturn(true);

        String json = """
        {
            "nombreArtistico":"Karol G",
            "especialidad":"Cantante",
            "biografia":"Cantante urbana",
            "fechaDisponible":"2030-01-01T10:00:00",
            "disponible":true
        }
        """;

        mockMvc.perform(post("/api/v1/artistas/crear-artista")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET listar artistas con datos")
    void listarConDatos() throws Exception {

        Artista artista = new Artista(
                1,
                "Shakira",
                "Cantante",
                "Bio",
                LocalDateTime.now(),
                true);

        when(service.listarArtistas()).thenReturn(List.of(artista));

        mockMvc.perform(get("/api/v1/artistas/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET listar artistas vacio")
    void listarVacio() throws Exception {

        when(service.listarArtistas()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/artistas/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET especialidad con datos")
    void especialidadConDatos() throws Exception {

        Artista artista = new Artista();

        when(service.obtenerPorEspecialidad("Cantante"))
                .thenReturn(List.of(artista));

        mockMvc.perform(get("/api/v1/artistas/buscar-especialidad/Cantante"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET especialidad vacia")
    void especialidadVacia() throws Exception {

        when(service.obtenerPorEspecialidad("Bailarin"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/artistas/buscar-especialidad/Bailarin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET especialidad multiples")
    void especialidadMultiples() throws Exception {

        when(service.obtenerPorEspecialidad("Cantante"))
                .thenReturn(List.of(new Artista(), new Artista()));

        mockMvc.perform(get("/api/v1/artistas/buscar-especialidad/Cantante"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET disponibles true")
    void disponiblesTrue() throws Exception {

        when(service.obtenerPorDisponibilidad(true))
                .thenReturn(List.of(new Artista()));

        mockMvc.perform(get("/api/v1/artistas/buscar-disponible/true"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET disponibles false")
    void disponiblesFalse() throws Exception {

        when(service.obtenerPorDisponibilidad(false))
                .thenReturn(List.of(new Artista()));

        mockMvc.perform(get("/api/v1/artistas/buscar-disponible/false"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

