package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.model.CuponDescuento;
import com.example.MicroPostVenta.repository.CuponDescuentoRepository;
import com.example.MicroPostVenta.service.CuponDescuentoService;

@Service
@Transactional
public class CuponDescuentoServiceimpl implements CuponDescuentoService {

    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;

    @Override
    public List<CuponDescuento> obtenerCuponDescuentos() {
        return cuponDescuentoRepository.findAll();
    }

    @Override
    public CuponDescuento buscarCuponDescuento(int id_cupon_descuento) {
        return cuponDescuentoRepository.findById(id_cupon_descuento).orElse(new CuponDescuento());
    }

    @Override
    public CuponDescuento crearCuponDescuento(CuponDescuento cuponDescuento) {
        return cuponDescuentoRepository.save(cuponDescuento);
    }

    @Override
    public CuponDescuento actualizarCuponDescuento(CuponDescuento cuponDescuento) {
        return cuponDescuentoRepository.save(cuponDescuento);
    }

    @Override
    public int eliminarCuponDescuento(int id_cupon_descuento) {
        if (cuponDescuentoRepository.existsById(id_cupon_descuento)) {
            cuponDescuentoRepository.deleteById(id_cupon_descuento);
            return 1;
        }
        return 0;
    }
}
