package com.example.MicroTiendaUbicacion.service;

import java.util.List;

import com.example.MicroTiendaUbicacion.dto.TiendaDTO;

public interface TiendaService {

    List<TiendaDTO.Response> listar();

    TiendaDTO.Response buscarPorId(Integer id);

    TiendaDTO.Response guardar(TiendaDTO.Request request);

    TiendaDTO.Response actualizar(Integer id, TiendaDTO.Request request);

    void eliminar(Integer id);
}