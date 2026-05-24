package com.venta.Streaming.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venta.Streaming.DTO.StreamingDTO;
import com.venta.Streaming.Model.Streaming;
import com.venta.Streaming.Repository.StreamingRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StreamingService {

    @Autowired
    private StreamingRepository streamingRepository;

    public Boolean guardarStreaming(StreamingDTO streamingDTO) {

        Streaming streaming = new Streaming();

        streaming.setEventoId(streamingDTO.getEventoId());
        streaming.setPlataforma(streamingDTO.getPlataforma());
        streaming.setUrlAcceso(streamingDTO.getUrlAcceso());
        streaming.setCodigoAcceso(streamingDTO.getCodigoAcceso());
        streaming.setFechaInicio(streamingDTO.getFechaInicio());
        streaming.setFechaFin(streamingDTO.getFechaFin());
        streaming.setActivo(streamingDTO.getActivo());

        streamingRepository.save(streaming);

        return true;
    }

    public List<Streaming> listarStreaming() {
        return streamingRepository.findAll();
    }

    public List<Streaming> obtenerPorEvento(Integer eventoId) {
        return streamingRepository.findByEventoId(eventoId);
    }

    public List<Streaming> obtenerPorActivo(Boolean activo) {
        return streamingRepository.findByActivo(activo);
    }

    public List<Streaming> obtenerPorCodigoAcceso(String codigoAcceso) {
        return streamingRepository.findByCodigoAcceso(codigoAcceso);
    }
}