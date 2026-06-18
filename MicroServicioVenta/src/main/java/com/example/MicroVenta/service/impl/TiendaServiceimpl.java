package com.example.MicroVenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.model.Tienda;
import com.example.MicroVenta.repository.TiendaRepository;
import com.example.MicroVenta.service.TiendaService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TiendaServiceimpl implements TiendaService {

    private final TiendaRepository tiendaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Tienda> listarTodos() {
        return tiendaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Tienda buscarPorId(int id) {
        return tiendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public Tienda crear(Tienda request) {
        return tiendaRepository.save(request);
    }

    @Override
    @Transactional
    public Tienda actualizar(int id, Tienda request) {
        Tienda tienda = tiendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id: " + id));
        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setComuna(request.getComuna());
        tienda.setRegion(request.getRegion());
        return tiendaRepository.save(tienda);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!tiendaRepository.existsById(id))
            throw new RuntimeException("Tienda no encontrada con id: " + id);
        tiendaRepository.deleteById(id);
    }
}
