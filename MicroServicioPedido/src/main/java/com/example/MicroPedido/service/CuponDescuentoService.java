package com.example.MicroPedido.service;

import java.util.List;
import com.example.MicroPedido.model.CuponDescuento;

public interface CuponDescuentoService {

    List<CuponDescuento> getAllCupones();

    CuponDescuento getCuponDescuentoById(int id_cupon);

    CuponDescuento saveCuponDescuento(CuponDescuento cuponDescuento);

    int updateCuponDescuento(CuponDescuento cuponDescuento);

    int deleteCuponDescuento(int id_cupon);
}