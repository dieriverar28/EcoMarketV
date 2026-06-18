package com.example.MicroEnvio.service;

import com.example.MicroEnvio.dto.ClienteDTO;
import java.util.List;

public interface ClienteService {
    List<ClienteDTO.Response> listarTodos();
    ClienteDTO.Response buscarPorId(int id);
    ClienteDTO.Response crear(ClienteDTO.Request request);
    ClienteDTO.Response actualizar(int id, ClienteDTO.Request request);
    void eliminar(int id);
}
