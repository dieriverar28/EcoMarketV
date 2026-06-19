package com.example.MicroPedido.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPedido.entity.DetallePedido;
import com.example.MicroPedido.repository.DetallePedidoRepository;
import com.example.MicroPedido.service.DetallePedidoService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    // OBTENER TODOS
    @Override
    public List<DetallePedido> getDetallesPedido() {
        return detallePedidoRepository.obtenerDetallePedidos();
    }

    // OBTENER POR ID
    @Override
    public DetallePedido getDetallePedidoById(int id_detalle) {
        return detallePedidoRepository.buscarDetallePedido(id_detalle);
    }

    // CREAR
    @Override
    public DetallePedido saveDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    // ACTUALIZAR
    @Override
    public int updateDetallePedido(DetallePedido detallePedido) {
        return detallePedidoRepository.modificarDetallePedido(
                detallePedido.getId_detalle(),
                detallePedido.getCantidad(),
                detallePedido.getPrecio_unitario()
        );
    }

    // ELIMINAR
    @Override
    public int deleteDetallePedido(int id_detalle) {
        return detallePedidoRepository.eliminarDetallePedido(id_detalle);
    }
}
