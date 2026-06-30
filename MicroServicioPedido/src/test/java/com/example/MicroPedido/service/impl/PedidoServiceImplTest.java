package com.example.MicroPedido.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.entity.Cliente;
import com.example.MicroPedido.entity.Pedido;
import com.example.MicroPedido.entity.Tienda;
import com.example.MicroPedido.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Test
    void listarPedidos() {
        Pedido pedido = new Pedido(1, new Cliente(), new Tienda(), "PENDIENTE", 1, new Date(0), 5000);
        when(pedidoRepository.obtenerPedidos()).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoService.getPedidos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEstado()).isEqualTo("PENDIENTE");
    }

    @Test
    void buscarPedidoPorId() {
        Pedido pedido = new Pedido(2, new Cliente(), new Tienda(), "PROCESO", 2, new Date(1), 7000);
        when(pedidoRepository.buscarPedido(2)).thenReturn(pedido);

        Pedido result = pedidoService.getPedido(2);

        assertThat(result.getId_pedido()).isEqualTo(2);
        assertThat(result.getEstado()).isEqualTo("PROCESO");
    }

    @Test
    void guardarPedido() {
        Pedido pedido = new Pedido(3, new Cliente(), new Tienda(), "FINALIZADO", null, new Date(2), 8000);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoService.savePedido(pedido);

        assertThat(result).isEqualTo(pedido);
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void actualizarPedido() {
        Pedido pedido = new Pedido(4, new Cliente(), new Tienda(), "ANULADO", null, new Date(3), 9000);

        int result = pedidoService.updatePedido(pedido);

        assertThat(result).isEqualTo(1);
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void eliminarPedido() {
        Pedido pedido = new Pedido(5, new Cliente(), new Tienda(), "ELIMINADO", null, new Date(4), 1000);
        when(pedidoRepository.buscarPedido(5)).thenReturn(pedido);

        int result = pedidoService.deletePedido(5);

        assertThat(result).isEqualTo(1);
        verify(pedidoRepository).delete(pedido);
    }
}
