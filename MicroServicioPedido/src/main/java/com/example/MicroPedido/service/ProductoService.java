package com.example.MicroPedido.service;

import java.util.List;

import com.example.MicroPedido.dto.ProductoDTO;

public interface ProductoService {

    List<ProductoDTO.Response> listarTodos();

    List<ProductoDTO.Response> listarActivos();

    ProductoDTO.Response buscarPorId(int id);

    ProductoDTO.Response crear(ProductoDTO.Request request);

    ProductoDTO.Response actualizar(int id, ProductoDTO.Request request);

    int eliminar(int id);
}