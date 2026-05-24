package com.venta.eventos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.venta.eventos.DTO.EventoDTO;
import com.venta.eventos.Model.Evento;
import com.venta.eventos.Service.EventoService;
@RestController
@RequestMapping("/evento")
public class EventoController {

  @Autowired
  private EventoService service;

  @GetMapping
  public List<Evento> listar() {
    return service.listar();
  }

  @PostMapping
  public Evento guardar(@RequestBody EventoDTO dto) {

    Evento evento = new Evento();

    evento.setNombre(dto.getNombre());
    evento.setCategoria(dto.getCategoria());
    evento.setFecha(dto.getFecha());
    evento.setLugar(dto.getLugar());
    evento.setCapacidad(dto.getCapacidad());
    evento.setRecintoId(dto.getRecintoId());

    return service.guardar(evento);
  }

  @GetMapping("/{id}")
  public Evento buscarPorId(@PathVariable Long id) {
    return service.buscarPorId(id);
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }



}