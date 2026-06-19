package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.entity.Venta;

public interface VentaService {

    List<Venta> getVentas();

    Venta getVenta(int id_venta);

    Venta saveVenta(Venta venta);

    int updateVenta(Venta venta);

    int deleteVenta(int id_venta);
}

