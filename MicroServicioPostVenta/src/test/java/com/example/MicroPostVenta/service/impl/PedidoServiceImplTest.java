package com.example.MicroPostVenta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.CuponDescuento;
import com.example.MicroPostVenta.entity.Pedido;
import com.example.MicroPostVenta.entity.Tienda;
import com.example.MicroPostVenta.repository.PedidoRepository;

public class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceimpl pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarPedidos() {
        Pedido pedido = crearPedido();
        when(pedidoRepository.obtenerPedidos()).thenReturn(List.of(pedido));

        List<Pedido> resultado = pedidoService.getPedidos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(pedido.getId_pedido(), resultado.get(0).getId_pedido());
        assertEquals(pedido.getFecha_pedido(), resultado.get(0).getFecha_pedido());
    }

    @Test
    void buscarPedidoPorId() {
        Pedido pedido = crearPedido();
        when(pedidoRepository.buscarPedido(pedido.getId_pedido())).thenReturn(pedido);

        Pedido resultado = pedidoService.getPedido(pedido.getId_pedido());

        assertNotNull(resultado);
        assertEquals(pedido.getId_pedido(), resultado.getId_pedido());
    }

    @Test
    void guardarPedido() {
        Pedido pedido = crearPedido();
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.savePedido(pedido);

        assertNotNull(resultado);
        assertEquals(pedido.getFecha_pedido(), resultado.getFecha_pedido());
    }

    @Test
    void actualizarPedido() {
        Pedido pedido = crearPedido();
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        int resultado = pedidoService.updatePedido(pedido);

        assertEquals(1, resultado);
    }

    @Test
    void eliminarPedido() {
        int id = 1;
        when(pedidoRepository.existsById(id)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(id);

        int resultado = pedidoService.deletePedido(id);

        assertEquals(1, resultado);
        verify(pedidoRepository).deleteById(id);
    }

    private Pedido crearPedido() {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(1);
        pedido.setCliente(crearCliente());
        pedido.setTienda(crearTienda());
        pedido.setEstado(true);
        pedido.setCupondescuento(crearCuponDescuento());
        pedido.setFecha_pedido(Date.valueOf("2026-12-31"));
        return pedido;
    }

    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Cliente prueba");
        cliente.setEmail("cliente@prueba.com");
        cliente.setTelefono("1234567890");
        cliente.setComuna(1);
        cliente.setDireccion_envio("Direccion prueba");
        cliente.setGenero(1);
        return cliente;
    }

    private Tienda crearTienda() {
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);
        tienda.setNombre("Tienda prueba");
        tienda.setDireccion("Direccion tienda");
        tienda.setComuna(1);
        tienda.setRegion(1);
        return tienda;
    }

    private CuponDescuento crearCuponDescuento() {
        CuponDescuento cupon = new CuponDescuento();
        cupon.setId_cupon_descuento(1);
        cupon.setCodigo(1001);
        cupon.setDescuento_pct(20);
        cupon.setDescuento_monto(5000);
        cupon.setFecha_expiracion(Date.valueOf("2026-12-31"));
        cupon.setActivo(true);
        return cupon;
    }
}

