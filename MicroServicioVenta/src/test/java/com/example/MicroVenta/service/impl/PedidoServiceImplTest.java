package com.example.MicroVenta.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.entity.Cliente;
import com.example.MicroVenta.entity.CuponDescuento;
import com.example.MicroVenta.entity.Pedido;
import com.example.MicroVenta.entity.Tienda;
import com.example.MicroVenta.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceimpl pedidoService;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId_pedido(1);
        pedido.setCliente(new Cliente());
        pedido.setTienda(new Tienda());
        pedido.setEstado(true);
        pedido.setCupondescuento(new CuponDescuento());
        pedido.setFecha_pedido(Date.valueOf("2024-01-15"));
    }

    @Test
    void listarTodos_deberiaRetornarListaDePedidos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> resultado = pedidoService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getId_pedido()).isEqualTo(1);
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_deberiaRetornarPedido_siExiste() {
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.buscarPorId(1);

        assertThat(resultado.getId_pedido()).isEqualTo(1);
        assertThat(resultado.isEstado()).isTrue();
    }

    @Test
    void buscarPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(pedidoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pedidoService.buscarPorId(99));

        assertThat(ex.getMessage()).contains("Pedido no encontrado con id: 99");
    }

    @Test
    void crear_deberiaGuardarYRetornarPedido() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.crear(pedido);

        assertThat(resultado.getId_pedido()).isEqualTo(1);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void actualizar_deberiaModificarYGuardarPedido() {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCliente(new Cliente());
        nuevoPedido.setTienda(new Tienda());
        nuevoPedido.setEstado(false);
        nuevoPedido.setCupondescuento(new CuponDescuento());
        nuevoPedido.setFecha_pedido(Date.valueOf("2024-02-20"));

        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.actualizar(1, nuevoPedido);

        assertThat(resultado.getId_pedido()).isEqualTo(1);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void eliminar_deberiaEliminarPedido_siExiste() {
        when(pedidoRepository.existsById(1)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(1);

        pedidoService.eliminar(1);

        verify(pedidoRepository).existsById(1);
        verify(pedidoRepository).deleteById(1);
    }

    @Test
    void eliminar_deberiaLanzarExcepcion_siNoExiste() {
        when(pedidoRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pedidoService.eliminar(99));

        assertThat(ex.getMessage()).contains("Pedido no encontrado con id: 99");
    }

    @Test
    void getPedidos_deberiaRetornarTodosLosPedidos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> resultado = pedidoService.getPedidos();

        assertThat(resultado).hasSize(1);
        verify(pedidoRepository).findAll();
    }

    @Test
    void savePedido_deberiaGuardarPedido() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.savePedido(pedido);

        assertThat(resultado.getId_pedido()).isEqualTo(1);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void savePedidoPorId_deberiaRetornarId_siExiste() {
        when(pedidoRepository.existsById(1)).thenReturn(true);

        int resultado = pedidoService.savePedido(1);

        assertThat(resultado).isEqualTo(1);
    }

    @Test
    void savePedidoPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(pedidoRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pedidoService.savePedido(99));

        assertThat(ex.getMessage()).contains("Pedido no encontrado con id: 99");
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }
}
