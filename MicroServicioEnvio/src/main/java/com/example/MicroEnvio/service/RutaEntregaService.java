package com.example.MicroEnvio.service;

import com.example.MicroEnvio.dto.RutaEntregaDTO;
import java.util.List;

public interface RutaEntregaService {
    List<RutaEntregaDTO.Response> listarTodos();
    RutaEntregaDTO.Response buscarPorId(int id);
    RutaEntregaDTO.Response crear(RutaEntregaDTO.Request request);
    RutaEntregaDTO.Response actualizar(int id, RutaEntregaDTO.Request request);
    void eliminar(int id);
}
