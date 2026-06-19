package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.entity.Pedido;
import com.example.MicroPostVenta.repository.PedidoRepository;
import com.example.MicroPostVenta.service.PedidoService;

@Service
@Transactional
public class PedidoServiceimpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> getPedidos() {
        return pedidoRepository.obtenerPedidos();
    }

    @Override
    public Pedido getPedido(int id_pedido) {
        Pedido pedido = pedidoRepository.buscarPedido(id_pedido);
        if (pedido != null) {
            return pedido;
        }
        return new Pedido();
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public int updatePedido(Pedido pedido) {
        pedidoRepository.save(pedido);
        return 1;
    }

    @Override
    public int deletePedido(int id_pedido) {
        if (pedidoRepository.existsById(id_pedido)) {
            pedidoRepository.deleteById(id_pedido);
            return 1;
        }
        return 0;
    }
}

