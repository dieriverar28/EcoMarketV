package com.example.MicroInventario.service.impl;

import com.example.MicroInventario.dto.TiendaDTO;
import com.example.MicroInventario.entity.Tienda;
import com.example.MicroInventario.repository.TiendaRepository;
import com.example.MicroInventario.service.TiendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Override
    public List<TiendaDTO.Response> listar() {

        return tiendaRepository.findAll().stream().map(tienda ->
                new TiendaDTO.Response(
                        tienda.getId_tienda(),
                        tienda.getNombre(),
                        tienda.getDireccion(),
                        tienda.getId_comuna(),
                        tienda.getId_region()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public TiendaDTO.Response buscarPorId(int id) {

        Tienda tienda = tiendaRepository.findById(id).orElse(null);

        if (tienda == null) {
            return null;
        }

        return new TiendaDTO.Response(
                tienda.getId_tienda(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getId_comuna(),
                tienda.getId_region()
        );
    }

    @Override
    public TiendaDTO.Response guardar(TiendaDTO.Request request) {

        Tienda tienda = new Tienda();

        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setId_comuna(request.getId_comuna());
        tienda.setId_region(request.getId_region());

        Tienda guardada = tiendaRepository.save(tienda);

        return new TiendaDTO.Response(
                guardada.getId_tienda(),
                guardada.getNombre(),
                guardada.getDireccion(),
                guardada.getId_comuna(),
                guardada.getId_region()
        );
    }

    @Override
    public TiendaDTO.Response actualizar(int id, TiendaDTO.Request request) {

        Tienda tienda = tiendaRepository.findById(id).orElse(null);

        if (tienda == null) {
            return null;
        }

        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setId_comuna(request.getId_comuna());
        tienda.setId_region(request.getId_region());

        Tienda actualizada = tiendaRepository.save(tienda);

        return new TiendaDTO.Response(
                actualizada.getId_tienda(),
                actualizada.getNombre(),
                actualizada.getDireccion(),
                actualizada.getId_comuna(),
                actualizada.getId_region()
        );
    }

    @Override
    public void eliminar(int id) {

        tiendaRepository.deleteById(id);
    }
}