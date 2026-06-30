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
import com.example.MicroPostVenta.entity.Pedido;
import com.example.MicroPostVenta.entity.Tienda;
import com.example.MicroPostVenta.entity.Venta;
import com.example.MicroPostVenta.repository.VentaRepository;

public class VentaServiceImplTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceimpl ventaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarVentas() {
        Venta venta = crearVenta();
        when(ventaRepository.obtenerVentas()).thenReturn(List.of(venta));

        List<Venta> resultado = ventaService.getVentas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(venta.getTipo_documento(), resultado.get(0).getTipo_documento());
    }

    @Test
    void buscarVentaPorId() {
        Venta venta = crearVenta();
        when(ventaRepository.buscarVenta(venta.getId_venta())).thenReturn(venta);

        Venta resultado = ventaService.getVenta(venta.getId_venta());

        assertNotNull(resultado);
        assertEquals(venta.getId_venta(), resultado.getId_venta());
    }

    @Test
    void guardarVenta() {
        Venta venta = crearVenta();
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        Venta resultado = ventaService.saveVenta(venta);

        assertNotNull(resultado);
        assertEquals(venta.getTotal_neto(), resultado.getTotal_neto());
    }

    @Test
    void actualizarVenta() {
        Venta venta = crearVenta();
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        int resultado = ventaService.updateVenta(venta);

        assertEquals(1, resultado);
    }

    @Test
    void eliminarVenta() {
        int id = 1;
        when(ventaRepository.existsById(id)).thenReturn(true);
        doNothing().when(ventaRepository).deleteById(id);

        int resultado = ventaService.deleteVenta(id);

        assertEquals(1, resultado);
        verify(ventaRepository).deleteById(id);
    }

    private Venta crearVenta() {
        Venta venta = new Venta();
        venta.setId_venta(1);
        venta.setPedido(crearPedido());
        venta.setTienda(crearTienda());
        venta.setCliente(crearCliente());
        venta.setFecha_venta(Date.valueOf("2026-12-31"));
        venta.setTotal_neto(10000);
        venta.setDescuento_aplicado(1000);
        venta.setTipo_documento("FACTURA");
        return venta;
    }

    private Pedido crearPedido() {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(1);
        pedido.setEstado(true);
        pedido.setCliente(crearCliente());
        pedido.setTienda(crearTienda());
        pedido.setCupondescuento(null);
        pedido.setFecha_pedido(Date.valueOf("2026-12-31"));
        return pedido;
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
}

