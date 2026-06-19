package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.entity.Pedido;

import jakarta.validation.Valid;

public interface PedidoService {
    List<Pedido> listarTodos();
    Pedido buscarPorId(int id);
    Pedido crear(Pedido request);
    Pedido actualizar(int id, Pedido request);
    void eliminar(int id);
    List<Pedido> getPedidos();
    Pedido savePedido(Pedido pedido);
    int savePedido(int id_pedido);

}

