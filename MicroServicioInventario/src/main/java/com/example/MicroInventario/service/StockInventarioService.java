package com.example.MicroInventario.service;

import com.example.MicroInventario.dto.StockInventarioDTO;
import java.util.List;

public interface StockInventarioService {

    List<StockInventarioDTO.Response> listar();

    StockInventarioDTO.Response buscarPorId(int id);

    StockInventarioDTO.Response guardar(StockInventarioDTO.Request request);

    StockInventarioDTO.Response actualizar(int id, StockInventarioDTO.Request request);

    void eliminar(int id);
}