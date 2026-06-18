package com.example.MicroEnvio.service;

import com.example.MicroEnvio.dto.VentaDTO;
import java.util.List;

public interface VentaService {
    List<VentaDTO.Response> listarTodos();
    VentaDTO.Response buscarPorId(int id);
    VentaDTO.Response crear(VentaDTO.Request request);
    VentaDTO.Response actualizar(int id, VentaDTO.Request request);
    void eliminar(int id);
}
