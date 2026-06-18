package com.example.MicroPedido.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPedido.dto.TiendaDTO;
import com.example.MicroPedido.model.Tienda;
import com.example.MicroPedido.repository.TiendaRepository;
import com.example.MicroPedido.service.TiendaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiendaServiceImpl implements TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Override
    public List<TiendaDTO.Response> listarTiendas() {

        return tiendaRepository.obtenerTiendas()
                .stream()
                .map(this::convertirResponse)
                .toList();
    }

    @Override
    public TiendaDTO.Response buscarTienda(int id_tienda) {

        Tienda tienda = tiendaRepository.buscarTienda(id_tienda);

        if (tienda == null) {
            tienda = new Tienda();
        }

        return convertirResponse(tienda);
    }

    @Override
    public TiendaDTO.Response guardarTienda(TiendaDTO.Request request) {

        Tienda tienda = new Tienda();

        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setId_comuna(request.getId_comuna());

        Tienda guardada = tiendaRepository.save(tienda);

        return convertirResponse(guardada);
    }

    @Override
    public TiendaDTO.Response actualizarTienda(int id_tienda, TiendaDTO.Request request) {

        Tienda tienda = tiendaRepository.buscarTienda(id_tienda);

        if (tienda == null) {
            tienda = new Tienda();
        }

        tienda.setId_tienda(id_tienda);
        tienda.setNombre(request.getNombre());
        tienda.setDireccion(request.getDireccion());
        tienda.setId_comuna(request.getId_comuna());

        Tienda actualizada = tiendaRepository.save(tienda);

        return convertirResponse(actualizada);
    }

    @Override
    public int eliminarTienda(int id_tienda) {

        tiendaRepository.deleteById(id_tienda);
        return 1;
    }

    private TiendaDTO.Response convertirResponse(Tienda tienda) {

        return new TiendaDTO.Response(
                tienda.getId_tienda(),
                tienda.getNombre(),
                tienda.getDireccion(),
                tienda.getId_comuna());
    }
}