package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.model.Tienda;

public interface TiendaService {

    List<Tienda> getTiendas();

    Tienda getTienda(int id_tienda);

    Tienda saveTienda(Tienda tienda);

    Tienda updateTienda(int id_tienda, Tienda tienda);

    int deleteTienda(int id_tienda);
}
