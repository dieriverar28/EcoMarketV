package com.example.MicroTiendaUbicacion.service;

import java.util.List;

import com.example.MicroTiendaUbicacion.dto.HorarioTiendaDTO;

public interface HorarioTiendaService {

    List<HorarioTiendaDTO.Response> listar();

    HorarioTiendaDTO.Response buscarPorId(Integer id);

    HorarioTiendaDTO.Response guardar(HorarioTiendaDTO.Request request);

    HorarioTiendaDTO.Response actualizar(Integer id, HorarioTiendaDTO.Request request);

    void eliminar(Integer id);

    List<HorarioTiendaDTO.Response> buscarPorTienda(Integer id_tienda);
}