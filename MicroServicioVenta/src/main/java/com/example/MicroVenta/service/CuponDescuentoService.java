package com.example.MicroVenta.service;

import java.util.List;

import com.example.MicroVenta.entity.CuponDescuento;

import jakarta.validation.Valid;



public interface CuponDescuentoService {
    List<CuponDescuento> listarTodos();
    CuponDescuento buscarPorId(int id);
    CuponDescuento crear(CuponDescuento request);
    CuponDescuento actualizar(int id, CuponDescuento request);
    void eliminar(int id);
    List<CuponDescuento> getAllCupones();
    CuponDescuento saveCuponDescuento(CuponDescuento cuponDescuento);
    CuponDescuento getCuponDescuentoById(int id_cupon_descuento);
    CuponDescuento saveCuponDescuento(int id_cupon_descuento);
    int deleteCuponDescuento(int id_cupon_descuento);

}

