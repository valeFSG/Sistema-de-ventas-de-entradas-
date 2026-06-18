
package com.Venta.Promotores.Controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Venta.Promotores.DTO.PromotorDTO;
import com.Venta.Promotores.Model.Promotor;
import com.Venta.Promotores.Service.PromotorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PromotorController.class)
public class PromotorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PromotorService service;

    @Test
    @DisplayName("POST crear promotor exitoso")
    void crearPromotorExitoso() throws Exception {

        when(service.guardarPromotor(org.mockito.ArgumentMatchers.any(PromotorDTO.class)))
                .thenReturn(true);

        String json = """
        {
            "nombre":"Valentina",
            "correo":"vale@gmail.com",
            "telefono":"987654321",
            "comision":10.5,
            "estado":"Activo"
        }
        """;

        mockMvc.perform(post("/api/v1/promotores/crear-promotor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST crear promotor error")
    void crearPromotorError() throws Exception {

        when(service.guardarPromotor(org.mockito.ArgumentMatchers.any(PromotorDTO.class)))
                .thenReturn(false);

        String json = """
        {
            "nombre":"Valentina",
            "correo":"vale@gmail.com",
            "telefono":"987654321",
            "comision":10.5,
            "estado":"Activo"
        }
        """;

        mockMvc.perform(post("/api/v1/promotores/crear-promotor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST crear promotor exitoso 2")
    void crearPromotorExitoso2() throws Exception {

        when(service.guardarPromotor(org.mockito.ArgumentMatchers.any(PromotorDTO.class)))
                .thenReturn(true);

        String json = """
        {
            "nombre":"Axel",
            "correo":"axel@gmail.com",
            "telefono":"912345678",
            "comision":20.0,
            "estado":"Activo"
        }
        """;

        mockMvc.perform(post("/api/v1/promotores/crear-promotor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET listar con datos")
    void listarConDatos() throws Exception {

        Promotor promotor = new Promotor(
                1,
                "Valentina",
                "vale@gmail.com",
                "987654321",
                10.5,
                "Activo");

        when(service.listarPromotores()).thenReturn(List.of(promotor));

        mockMvc.perform(get("/api/v1/promotores/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET listar vacío")
    void listarVacio() throws Exception {

        when(service.listarPromotores()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/promotores/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET estado con datos")
    void estadoConDatos() throws Exception {

        when(service.obtenerPorEstado("Activo"))
                .thenReturn(List.of(new Promotor()));

        mockMvc.perform(get("/api/v1/promotores/buscar-estado/Activo"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET estado vacío")
    void estadoVacio() throws Exception {

        when(service.obtenerPorEstado("Suspendido"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/promotores/buscar-estado/Suspendido"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET estado múltiples")
    void estadoMultiples() throws Exception {

        when(service.obtenerPorEstado("Activo"))
                .thenReturn(List.of(new Promotor(), new Promotor()));

        mockMvc.perform(get("/api/v1/promotores/buscar-estado/Activo"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET correo con datos")
    void correoConDatos() throws Exception {

        when(service.obtenerPorCorreo("vale@gmail.com"))
                .thenReturn(List.of(new Promotor()));

        mockMvc.perform(get("/api/v1/promotores/buscar-correo/vale@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET correo vacío")
    void correoVacio() throws Exception {

        when(service.obtenerPorCorreo("nadie@gmail.com"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/promotores/buscar-correo/nadie@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

