package com.venta.ventas.Controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.venta.ventas.Model.Venta;
import com.venta.ventas.Service.VentaService;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VentaService service;

    @Test
    @DisplayName("GET /ventas -> Lista con datos")
    void listarConDatos() throws Exception {

        Venta venta = new Venta();
        venta.setId(1L);
        venta.setCliente("Valentina");

        when(service.listar()).thenReturn(List.of(venta));

        mockMvc.perform(get("/ventas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].cliente").value("Valentina"));
    }

    @Test
    @DisplayName("GET /ventas -> Lista vacía")
    void listarVacio() throws Exception {

        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/ventas"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /ventas/{id} -> Existe")
    void buscarPorIdExiste() throws Exception {

        Venta venta = new Venta();
        venta.setId(1L);
        venta.setCliente("Valentina");
        venta.setEvento("Concierto");

        when(service.buscarPorId(1L)).thenReturn(venta);

        mockMvc.perform(get("/ventas/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cliente").value("Valentina"));
    }

    @Test
    @DisplayName("GET /ventas/{id} -> No existe")
    void buscarPorIdNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(get("/ventas/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /ventas -> Datos válidos")
    void guardarValido() throws Exception {

        Venta venta = new Venta();
        venta.setId(1L);
        venta.setCliente("Valentina");

        when(service.guardar(any(Venta.class)))
                .thenReturn(venta);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "cantidadEntradas":2,
            "total":50000,
            "metodoPago":"Debito",
            "eventoId":1
        }
        """;

        mockMvc.perform(post("/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("POST /ventas -> Datos inválidos")
    void guardarInvalido() throws Exception {

        String json = """
        {
            "cliente":"",
            "evento":"",
            "cantidadEntradas":0,
            "total":0,
            "metodoPago":"",
            "eventoId":null
        }
        """;

        mockMvc.perform(post("/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("PUT /ventas/{id} -> Existe")
    void actualizarExiste() throws Exception {

        Venta venta = new Venta();
        venta.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(venta);
        when(service.guardar(any(Venta.class))).thenReturn(venta);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "cantidadEntradas":3,
            "total":70000,
            "metodoPago":"Credito",
            "eventoId":1
        }
        """;

        mockMvc.perform(put("/ventas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /ventas/{id} -> No existe")
    void actualizarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "cantidadEntradas":3,
            "total":70000,
            "metodoPago":"Credito",
            "eventoId":1
        }
        """;

        mockMvc.perform(put("/ventas/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /ventas/{id} -> Existe")
    void eliminarExiste() throws Exception {

        Venta venta = new Venta();
        venta.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(venta);

        mockMvc.perform(delete("/ventas/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /ventas/{id} -> No existe")
    void eliminarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/ventas/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

