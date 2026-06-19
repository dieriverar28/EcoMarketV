package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.entity.Tienda;

public interface TiendaService {
    List<Tienda> listarTodos();
    Tienda buscarPorId(int id);
    Tienda crear(Tienda request);
    Tienda actualizar(int id, Tienda request);
    void eliminar(int id);
}

