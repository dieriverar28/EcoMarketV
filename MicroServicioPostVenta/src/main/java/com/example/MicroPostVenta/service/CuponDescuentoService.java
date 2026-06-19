package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.entity.CuponDescuento;

public interface CuponDescuentoService {
    List<CuponDescuento> obtenerCuponDescuentos();

    CuponDescuento buscarCuponDescuento(int id_cupon_descuento);

    CuponDescuento crearCuponDescuento(CuponDescuento cuponDescuento);

    CuponDescuento actualizarCuponDescuento(CuponDescuento cuponDescuento);

    int eliminarCuponDescuento(int id_cupon_descuento);

}

