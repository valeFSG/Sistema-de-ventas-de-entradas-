package com.Venta.Validacion.Controller;

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

import com.Venta.Validacion.DTO.DTO;
import com.Venta.Validacion.Model.Validacion;
import com.Venta.Validacion.Service.ValidacionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ValidacionController.class)
public class ValidacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ValidacionService service;

    @Test
    @DisplayName("Listar validaciones con datos")
    void listarConDatos() throws Exception {

        Validacion v = new Validacion();
        v.setId(1);

        when(service.listarValidaciones())
                .thenReturn(List.of(v));

        mockMvc.perform(get("/api/v1/validaciones/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Listar validaciones vacío")
    void listarVacio() throws Exception {

        when(service.listarValidaciones())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/validaciones/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Crear validacion exitoso")
    void crearValidacionExitoso() throws Exception {

        when(service.guardarValidacion(any(DTO.class)))
                .thenReturn(true);

        String json = """
        {
          "ticketId":1,
          "codigoEntrada":"ABC123",
          "estado":"VALIDADO",
          "fechaValidacion":"2030-01-01T10:00:00",
          "observacion":"OK"
        }
        """;

        mockMvc.perform(post("/api/v1/validaciones/crear-validacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Crear validacion error")
    void crearValidacionError() throws Exception {

        when(service.guardarValidacion(any(DTO.class)))
                .thenReturn(false);

        String json = """
        {
          "ticketId":1,
          "codigoEntrada":"ABC123",
          "estado":"VALIDADO",
          "fechaValidacion":"2030-01-01T10:00:00",
          "observacion":"OK"
        }
        """;

        mockMvc.perform(post("/api/v1/validaciones/crear-validacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Buscar ticket con datos")
    void ticketConDatos() throws Exception {

        Validacion v = new Validacion();
        v.setId(1);

        when(service.obtenerPorTicket(1))
                .thenReturn(List.of(v));

        mockMvc.perform(get("/api/v1/validaciones/buscar-ticket/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar ticket vacío")
    void ticketVacio() throws Exception {

        when(service.obtenerPorTicket(99))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/validaciones/buscar-ticket/99"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar estado con datos")
    void estadoConDatos() throws Exception {

        Validacion v = new Validacion();
        v.setId(1);

        when(service.obtenerPorEstado("VALIDADO"))
                .thenReturn(List.of(v));

        mockMvc.perform(get("/api/v1/validaciones/buscar-estado/VALIDADO"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar estado vacío")
    void estadoVacio() throws Exception {

        when(service.obtenerPorEstado("X"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/validaciones/buscar-estado/X"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar codigo con datos")
    void codigoConDatos() throws Exception {

        Validacion v = new Validacion();
        v.setId(1);

        when(service.obtenerPorCodigoEntrada("ABC123"))
                .thenReturn(List.of(v));

        mockMvc.perform(get("/api/v1/validaciones/buscar-codigo/ABC123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Buscar codigo vacío")
    void codigoVacio() throws Exception {

        when(service.obtenerPorCodigoEntrada("XXX"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/validaciones/buscar-codigo/XXX"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
