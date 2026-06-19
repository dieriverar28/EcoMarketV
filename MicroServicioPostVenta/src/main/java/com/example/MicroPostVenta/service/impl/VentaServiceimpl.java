package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.entity.Venta;
import com.example.MicroPostVenta.repository.VentaRepository;
import com.example.MicroPostVenta.service.VentaService;

@Service
@Transactional
public class VentaServiceimpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> getVentas() {
        return ventaRepository.obtenerVentas();
    }

    @Override
    public Venta getVenta(int id_venta) {
        Venta venta = ventaRepository.buscarVenta(id_venta);
        if (venta != null) {
            return venta;
        }
        return new Venta();
    }

    @Override
    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public int updateVenta(Venta venta) {
        ventaRepository.save(venta);
        return 1;
    }

    @Override
    public int deleteVenta(int id_venta) {
        if (ventaRepository.existsById(id_venta)) {
            ventaRepository.deleteById(id_venta);
            return 1;
        }
        return 0;
    }
}

