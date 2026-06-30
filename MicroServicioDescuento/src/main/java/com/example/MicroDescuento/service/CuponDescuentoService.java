package com.example.MicroDescuento.service;

import java.util.List;

import com.example.MicroDescuento.dto.CuponDescuentoDTO;
import com.example.MicroDescuento.entity.CuponDescuento;

public interface CuponDescuentoService {

    List<CuponDescuentoDTO.Response> getAllCupones();

    CuponDescuentoDTO.Response getCuponDescuentoById(int id_cupon_descuento);

    CuponDescuentoDTO.Response saveCuponDescuento(CuponDescuento cuponDescuento);

    int updateCuponDescuento(CuponDescuento cuponDescuento);

    int deleteCuponDescuento(int id_cupon_descuento);
}

