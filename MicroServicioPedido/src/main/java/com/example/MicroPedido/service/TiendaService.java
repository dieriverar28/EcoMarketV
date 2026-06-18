package com.example.MicroPedido.service;

import java.util.List;

import com.example.MicroPedido.dto.TiendaDTO;

public interface TiendaService {

    List<TiendaDTO.Response> listarTiendas();

    TiendaDTO.Response buscarTienda(int id_tienda);

    TiendaDTO.Response guardarTienda(TiendaDTO.Request request);

    TiendaDTO.Response actualizarTienda(int id_tienda, TiendaDTO.Request request);

    int eliminarTienda(int id_tienda);
}