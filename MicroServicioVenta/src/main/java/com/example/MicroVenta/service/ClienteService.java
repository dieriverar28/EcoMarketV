package com.example.MicroVenta.service;

import java.util.List;

import com.example.MicroVenta.dto.ClienteDTO;

public interface ClienteService {
    List<ClienteDTO.Response> listarTodos();
    ClienteDTO.Response buscarPorId(int id);
    ClienteDTO.Response crear(ClienteDTO.Request request);
    ClienteDTO.Response actualizar(int id, ClienteDTO.Request request);
    void eliminar(int id);

}
