package com.example.MicroCliente.service;

import com.example.MicroCliente.dto.GeneroDTO;
import java.util.List;

public interface GeneroService {

    List<GeneroDTO.Response> listarGeneros();

    GeneroDTO.Response obtenerGeneroPorId(Integer id_genero);

    GeneroDTO.Response crearGenero(GeneroDTO.Request request);

    GeneroDTO.Response actualizarGenero(Integer id_genero, GeneroDTO.Request request);

    void eliminarGenero(Integer id_genero);
}