package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.model.Tienda;
import com.example.MicroPostVenta.repository.TiendaRepository;
import com.example.MicroPostVenta.service.TiendaService;

@Service
@Transactional
public class TiendaServiceimpl implements TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Override
    public List<Tienda> getTiendas() {
        return tiendaRepository.obtenerTienda();
    }

    @Override
    public Tienda getTienda(int id_tienda) {
        Tienda tienda = tiendaRepository.buscarTienda(id_tienda);
        if (tienda != null) {
            return tienda;
        }
        return new Tienda();
    }

    @Override
    public Tienda saveTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    @Override
    public Tienda updateTienda(int id_tienda, Tienda tienda) {
        Tienda tiendaExistente = getTienda(id_tienda);
        if (tiendaExistente != null && tiendaExistente.getId_tienda() != 0) {
            tienda.setId_tienda(id_tienda);
            return tiendaRepository.save(tienda);
        }
        return null;
    }

    @Override
    public int deleteTienda(int id_tienda) {
        if (tiendaRepository.existsById(id_tienda)) {
            tiendaRepository.deleteById(id_tienda);
            return 1;
        }
        return 0;
    }
}
