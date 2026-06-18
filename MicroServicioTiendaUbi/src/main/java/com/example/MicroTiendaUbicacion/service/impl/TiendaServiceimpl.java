package com.example.MicroTiendaUbicacion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MicroTiendaUbicacion.dto.TiendaDTO;
import com.example.MicroTiendaUbicacion.entity.Tienda;
import com.example.MicroTiendaUbicacion.repository.TiendaRepository;
import com.example.MicroTiendaUbicacion.service.TiendaService;

@Service
public class TiendaServiceImpl implements TiendaService {

    private final TiendaRepository repository;

    public TiendaServiceImpl(TiendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TiendaDTO.Response> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TiendaDTO.Response buscarPorId(Integer id) {

        Tienda tienda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

        return toResponse(tienda);
    }

    @Override
    public TiendaDTO.Response guardar(TiendaDTO.Request request) {

        Tienda tienda = new Tienda();

        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setTelefono(request.getTelefono());
        tienda.setIdComuna(request.getId_comuna());
        tienda.setIdRegion(request.getId_region());
        tienda.setCodigo_postal(request.getCodigo_postal());
        tienda.setActiva(request.getActiva());

        return toResponse(repository.save(tienda));
    }

    @Override
    public TiendaDTO.Response actualizar(Integer id, TiendaDTO.Request request) {

        Tienda tienda = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setTelefono(request.getTelefono());
        tienda.setIdComuna(request.getId_comuna());
        tienda.setIdRegion(request.getId_region());
        tienda.setCodigo_postal(request.getCodigo_postal());
        tienda.setActiva(request.getActiva());

        return toResponse(repository.save(tienda));
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

private TiendaDTO.Response toResponse(Tienda tienda) {

    return new TiendaDTO.Response(
            tienda.getId_tienda(),
            tienda.getNombre(),
            tienda.getDireccion(),
            tienda.getTelefono(),
            tienda.getCodigo_postal(),
            tienda.getActiva(),
            tienda.getIdComuna(),
            tienda.getIdRegion(),
            null
    );
    }

}
