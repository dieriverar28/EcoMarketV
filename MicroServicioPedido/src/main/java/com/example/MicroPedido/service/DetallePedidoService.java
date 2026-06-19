package com.example.MicroPedido.service;

import java.util.List;
import com.example.MicroPedido.entity.DetallePedido;

public interface DetallePedidoService {

    List<DetallePedido> getDetallesPedido();

    DetallePedido getDetallePedidoById(int id_detalle);

    DetallePedido saveDetallePedido(DetallePedido detallePedido);

    int updateDetallePedido(DetallePedido detallePedido);

    int deleteDetallePedido(int id_detalle);
}
