package com.example.MicroInventario.service;

import com.example.MicroInventario.dto.TiendaDTO;
import java.util.List;

public interface TiendaService {

    List<TiendaDTO.Response> listar();

    TiendaDTO.Response buscarPorId(int id);

    TiendaDTO.Response guardar(TiendaDTO.Request request);

    TiendaDTO.Response actualizar(int id, TiendaDTO.Request request);

    void eliminar(int id);
}