package com.example.MicroVenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.model.CuponDescuento;
import com.example.MicroVenta.repository.CuponDescuentoRepository;
import com.example.MicroVenta.service.CuponDescuentoService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuponDescuentoServiceimpl implements CuponDescuentoService {

    private final CuponDescuentoRepository cuponDescuentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CuponDescuento> listarTodos() {
        return cuponDescuentoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CuponDescuento buscarPorId(int id) {
        return cuponDescuentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupón descuento no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public CuponDescuento crear(CuponDescuento request) {
        return cuponDescuentoRepository.save(request);
    }

    @Override
    @Transactional
    public CuponDescuento actualizar(int id, CuponDescuento request) {
        CuponDescuento cupon = cuponDescuentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cupón descuento no encontrado con id: " + id));
        cupon.setCodigo(request.getCodigo());
        cupon.setDescuento_pct(request.getDescuento_pct());
        cupon.setDescuento_monto(request.getDescuento_monto());
        cupon.setFecha_expiracion(request.getFecha_expiracion());
        cupon.setActivo(request.isActivo());
        return cuponDescuentoRepository.save(cupon);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!cuponDescuentoRepository.existsById(id))
            throw new RuntimeException("Cupón descuento no encontrado con id: " + id);
        cuponDescuentoRepository.deleteById(id);
    }

    @Override
    public List<CuponDescuento> getAllCupones() {
        return cuponDescuentoRepository.findAll();
    }

    @Override
    public CuponDescuento saveCuponDescuento(CuponDescuento cuponDescuento) {
        return cuponDescuentoRepository.save(cuponDescuento);
    }

    @Override
    public CuponDescuento getCuponDescuentoById(int id_cupon_descuento) {
        return cuponDescuentoRepository.findById(id_cupon_descuento)
                .orElseThrow(() -> new RuntimeException("Cupón descuento no encontrado con id: " + id_cupon_descuento));
    }

    @Override
    public CuponDescuento saveCuponDescuento(int id_cupon_descuento) {
        return cuponDescuentoRepository.findById(id_cupon_descuento)
                .orElseThrow(() -> new RuntimeException("Cupón descuento no encontrado con id: " + id_cupon_descuento));
    }

    @Override
    public int deleteCuponDescuento(int id_cupon_descuento) {
        if (!cuponDescuentoRepository.existsById(id_cupon_descuento)) {
            return 0;
        }
        cuponDescuentoRepository.deleteById(id_cupon_descuento);
        return 1;
    }
}
