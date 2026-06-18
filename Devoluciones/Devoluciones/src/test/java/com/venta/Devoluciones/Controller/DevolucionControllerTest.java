package com.venta.Devoluciones.Controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.venta.Devoluciones.Model.Devolucion;
import com.venta.Devoluciones.Service.DevolucionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DevolucionController.class)
public class DevolucionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DevolucionService service;

    @Test
    @DisplayName("GET /devoluciones -> Lista con datos")
    void listarConDatos() throws Exception {

        Devolucion devolucion = new Devolucion();
        devolucion.setId(1L);
        devolucion.setCliente("Valentina");

        when(service.listar()).thenReturn(List.of(devolucion));

        mockMvc.perform(get("/devoluciones"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /devoluciones -> Lista vacía")
    void listarVacio() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/devoluciones"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /devoluciones/{id} -> Existe")
    void buscarPorIdExiste() throws Exception {

        Devolucion devolucion = new Devolucion();
        devolucion.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(devolucion);

        mockMvc.perform(get("/devoluciones/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /devoluciones/{id} -> No existe")
    void buscarPorIdNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/devoluciones/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /devoluciones -> Datos válidos")
    void guardarValido() throws Exception {

        Devolucion devolucion = new Devolucion();
        devolucion.setId(1L);

        when(service.guardar(any(Devolucion.class)))
                .thenReturn(devolucion);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "motivo":"No puede asistir",
            "monto":50000,
            "estado":"APROBADA"
        }
        """;

        mockMvc.perform(post("/devoluciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /devoluciones -> Error")
    void guardarInvalido() throws Exception {

        when(service.guardar(any(Devolucion.class)))
                .thenReturn(null);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "motivo":"No puede asistir",
            "monto":50000,
            "estado":"APROBADA"
        }
        """;

        mockMvc.perform(post("/devoluciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /devoluciones/{id} -> Existe")
    void actualizarExiste() throws Exception {

        Devolucion devolucion = new Devolucion();
        devolucion.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(devolucion);
        when(service.guardar(any(Devolucion.class))).thenReturn(devolucion);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "motivo":"Cambio de planes",
            "monto":70000,
            "estado":"PENDIENTE"
        }
        """;

        mockMvc.perform(put("/devoluciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /devoluciones/{id} -> No existe")
    void actualizarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "motivo":"Cambio de planes",
            "monto":70000,
            "estado":"PENDIENTE"
        }
        """;

        mockMvc.perform(put("/devoluciones/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /devoluciones/{id} -> Existe")
    void eliminarExiste() throws Exception {

        Devolucion devolucion = new Devolucion();
        devolucion.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(devolucion);

        mockMvc.perform(delete("/devoluciones/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /devoluciones/{id} -> No existe")
    void eliminarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/devoluciones/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}