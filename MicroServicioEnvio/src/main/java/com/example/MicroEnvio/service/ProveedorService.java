package com.example.MicroEnvio.service;

import com.example.MicroEnvio.dto.ProveedorDTO;
import java.util.List;

public interface ProveedorService {
    List<ProveedorDTO.Response> listarTodos();
    ProveedorDTO.Response buscarPorId(int id);
    ProveedorDTO.Response crear(ProveedorDTO.Request request);
    ProveedorDTO.Response actualizar(int id, ProveedorDTO.Request request);
    void eliminar(int id);
}
