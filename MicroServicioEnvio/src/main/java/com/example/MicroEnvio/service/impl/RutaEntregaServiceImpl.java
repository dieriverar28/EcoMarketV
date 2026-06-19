package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.RutaEntregaDTO;
import com.example.MicroEnvio.entity.RutaEntrega;
import com.example.MicroEnvio.repository.RutaEntregaRepository;
import com.example.MicroEnvio.service.RutaEntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RutaEntregaServiceImpl implements RutaEntregaService {

    private final RutaEntregaRepository rutaEntregaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RutaEntregaDTO.Response> listarTodos() {
        return rutaEntregaRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RutaEntregaDTO.Response buscarPorId(int id) {
        RutaEntrega r = rutaEntregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta de entrega no encontrada con id: " + id));
        return mapToResponse(r);
    }

    @Override
    @Transactional
    public RutaEntregaDTO.Response crear(RutaEntregaDTO.Request request) {
        RutaEntrega r = new RutaEntrega();
        r.setDescripcion(request.getDescripcion());
        return mapToResponse(rutaEntregaRepository.save(r));
    }

    @Override
    @Transactional
    public RutaEntregaDTO.Response actualizar(int id, RutaEntregaDTO.Request request) {
        RutaEntrega r = rutaEntregaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta de entrega no encontrada con id: " + id));
        r.setDescripcion(request.getDescripcion());
        return mapToResponse(rutaEntregaRepository.save(r));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!rutaEntregaRepository.existsById(id))
            throw new RuntimeException("Ruta de entrega no encontrada con id: " + id);
        rutaEntregaRepository.deleteById(id);
    }

    private RutaEntregaDTO.Response mapToResponse(RutaEntrega r) {
        return new RutaEntregaDTO.Response(r.getId(), r.getDescripcion());
    }
}

