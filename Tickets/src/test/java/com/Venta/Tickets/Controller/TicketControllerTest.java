package com.Venta.Tickets.Controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Venta.Tickets.DTO.TicketDTO;
import com.Venta.Tickets.Model.Ticket;
import com.Venta.Tickets.Service.TicketService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TicketService service;

    @Test
    @DisplayName("GET listar con datos")
    void listarConDatos() throws Exception {

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setCliente("Valentina");

        when(service.listarTickets()).thenReturn(List.of(ticket));

        mockMvc.perform(get("/api/v1/tickets/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET listar vacío")
    void listarVacio() throws Exception {

        when(service.listarTickets()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/tickets/listar"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET buscar cliente con datos")
    void buscarClienteConDatos() throws Exception {

        when(service.obtenerPorCliente("Valentina"))
                .thenReturn(List.of(new Ticket()));

        mockMvc.perform(get("/api/v1/tickets/buscar-cliente/Valentina"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET buscar cliente vacío")
    void buscarClienteVacio() throws Exception {

        when(service.obtenerPorCliente("Pedro"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/tickets/buscar-cliente/Pedro"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET buscar evento con datos")
    void buscarEventoConDatos() throws Exception {

        when(service.obtenerPorEvento("Concierto"))
                .thenReturn(List.of(new Ticket()));

        mockMvc.perform(get("/api/v1/tickets/buscar-evento/Concierto"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET buscar evento vacío")
    void buscarEventoVacio() throws Exception {

        when(service.obtenerPorEvento("Festival"))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/tickets/buscar-evento/Festival"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST crear ticket válido")
    void crearTicketValido() throws Exception {

        when(service.guardarTicket(any(TicketDTO.class)))
                .thenReturn(true);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "precio":25000,
            "cantidad":2,
            "ventaId":1
        }
        """;

        mockMvc.perform(post("/api/v1/tickets/crear-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST crear ticket error")
    void crearTicketError() throws Exception {

        when(service.guardarTicket(any(TicketDTO.class)))
                .thenReturn(false);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "precio":25000,
            "cantidad":2,
            "ventaId":1
        }
        """;

        mockMvc.perform(post("/api/v1/tickets/crear-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT actualizar existe")
    void actualizarExiste() throws Exception {

        Ticket ticket = new Ticket();
        ticket.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(ticket);
        when(service.guardar(any(Ticket.class))).thenReturn(ticket);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "precio":25000,
            "cantidad":2,
            "ventaId":1
        }
        """;

        mockMvc.perform(put("/api/v1/tickets/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT actualizar no existe")
    void actualizarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        String json = """
        {
            "cliente":"Valentina",
            "evento":"Concierto",
            "precio":25000,
            "cantidad":2,
            "ventaId":1
        }
        """;

        mockMvc.perform(put("/api/v1/tickets/actualizar/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE eliminar existe")
    void eliminarExiste() throws Exception {

        Ticket ticket = new Ticket();
        ticket.setId(1L);

        when(service.buscarPorId(1L)).thenReturn(ticket);

        mockMvc.perform(delete("/api/v1/tickets/eliminar/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE eliminar no existe")
    void eliminarNoExiste() throws Exception {

        when(service.buscarPorId(99L)).thenReturn(null);

        mockMvc.perform(delete("/api/v1/tickets/eliminar/99"))
                .andExpect(status().isNotFound());
    }
}