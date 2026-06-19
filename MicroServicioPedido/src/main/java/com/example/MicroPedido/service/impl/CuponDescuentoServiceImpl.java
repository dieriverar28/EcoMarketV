package com.example.MicroPedido.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPedido.entity.CuponDescuento;
import com.example.MicroPedido.repository.CuponDescuentoRepository;
import com.example.MicroPedido.service.CuponDescuentoService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CuponDescuentoServiceImpl implements CuponDescuentoService {

    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;

    // OBTENER TODOS
    @Override
    public List<CuponDescuento> getAllCupones() {
        return cuponDescuentoRepository.findAll();
    }

    // OBTENER POR ID
    @Override
    public CuponDescuento getCuponDescuentoById(int id_cupon) {
        CuponDescuento cupon = cuponDescuentoRepository.buscarCuponDescuento(id_cupon);
        return cupon != null ? cupon : new CuponDescuento();
    }

    // CREAR
    @Override
    public CuponDescuento saveCuponDescuento(CuponDescuento cuponDescuento) {
        return cuponDescuentoRepository.save(cuponDescuento);
    }

    // ACTUALIZAR
    @Override
    public int updateCuponDescuento(CuponDescuento cuponDescuento) {
        cuponDescuentoRepository.save(cuponDescuento);
        return 1;
    }

    // ELIMINAR
    @Override
    public int deleteCuponDescuento(int id_cupon) {
        CuponDescuento cupon = getCuponDescuentoById(id_cupon);
        cuponDescuentoRepository.delete(cupon);
        return 1;
    }
}
