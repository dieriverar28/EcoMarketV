package com.example.MicroEnvio.service;

import com.example.MicroEnvio.dto.EnvioDTO;
import java.util.List;

public interface EnvioService {
    List<EnvioDTO.Response> listarTodos();
    List<EnvioDTO.Response> listarActivos();
    List<EnvioDTO.Response> listarPorCliente(int id_cliente);
    EnvioDTO.Response buscarPorId(int id);
    EnvioDTO.Response crear(EnvioDTO.Request request);
    EnvioDTO.Response actualizar(int id, EnvioDTO.Request request);
    void eliminar(int id);
}
