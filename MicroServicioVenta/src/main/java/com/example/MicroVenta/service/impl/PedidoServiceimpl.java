package com.example.MicroVenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.entity.Pedido;
import com.example.MicroVenta.repository.PedidoRepository;
import com.example.MicroVenta.service.PedidoService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoServiceimpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido buscarPorId(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public Pedido crear(Pedido request) {
        return pedidoRepository.save(request);
    }

    @Override
    @Transactional
    public Pedido actualizar(int id, Pedido request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + id));
        pedido.setCliente(request.getCliente());
        pedido.setTienda(request.getTienda());
        pedido.setFecha_pedido(request.getFecha_pedido());
        pedido.setCupondescuento(request.getCupondescuento());
        pedido.setEstado(request.isEstado());
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!pedidoRepository.existsById(id))
            throw new RuntimeException("Pedido no encontrado con id: " + id);
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public int savePedido(int id_pedido) {
        if (!pedidoRepository.existsById(id_pedido)) {
            throw new RuntimeException("Pedido no encontrado con id: " + id_pedido);
        }
        return id_pedido;
    }
}

