package com.example.MicroVenta.service;

import java.util.List;

import com.example.MicroVenta.entity.Factura;

import jakarta.validation.Valid;

public interface FacturaService {
    List<Factura> listarTodos();
    Factura buscarPorId(int id);
    Factura crear(Factura request);
    Factura actualizar(int id, Factura request);
    void eliminar(int id);
    List<Factura> getFacturas();
    int saveFactura(Factura factura);
    Factura getFactura(int id_factura);
    int deleteFactura(int id_factura);
}

