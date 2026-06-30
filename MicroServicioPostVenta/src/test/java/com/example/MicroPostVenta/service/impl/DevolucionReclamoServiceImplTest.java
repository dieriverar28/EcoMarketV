package com.example.MicroPostVenta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.DevolucionReclamo;
import com.example.MicroPostVenta.entity.Producto;
import com.example.MicroPostVenta.entity.Venta;
import com.example.MicroPostVenta.repository.DevolucionReclamoRepository;

public class DevolucionReclamoServiceImplTest {

    @Mock
    private DevolucionReclamoRepository devolucionReclamoRepository;

    @InjectMocks
    private DevolucionReclamoServiceimpl devolucionReclamoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarDevolucionReclamos() {
        DevolucionReclamo devolucion = crearDevolucionReclamo();
        when(devolucionReclamoRepository.findAll()).thenReturn(List.of(devolucion));

        List<DevolucionReclamo> resultado = devolucionReclamoService.getDevolucionReclamos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(devolucion.getMotivo(), resultado.get(0).getMotivo());
    }

    @Test
    void buscarDevolucionReclamoPorId() {
        DevolucionReclamo devolucion = crearDevolucionReclamo();
        when(devolucionReclamoRepository.findById(devolucion.getId_devolucion())).thenReturn(Optional.of(devolucion));

        DevolucionReclamo resultado = devolucionReclamoService.getDevolucionReclamoById(devolucion.getId_devolucion());

        assertNotNull(resultado);
        assertEquals(devolucion.getId_devolucion(), resultado.getId_devolucion());
        assertEquals(devolucion.getMotivo(), resultado.getMotivo());
    }

    @Test
    void guardarDevolucionReclamo() {
        DevolucionReclamo devolucion = crearDevolucionReclamo();
        when(devolucionReclamoRepository.save(any(DevolucionReclamo.class))).thenReturn(devolucion);

        DevolucionReclamo resultado = devolucionReclamoService.saveDevolucionReclamo(devolucion);

        assertNotNull(resultado);
        assertEquals(devolucion.getMotivo(), resultado.getMotivo());
        assertEquals(devolucion.isEstado(), resultado.isEstado());
    }

    @Test
    void actualizarDevolucionReclamo() {
        DevolucionReclamo devolucion = crearDevolucionReclamo();
        devolucion.setMotivo("Cambio de motivo");
        when(devolucionReclamoRepository.save(any(DevolucionReclamo.class))).thenReturn(devolucion);

        int resultado = devolucionReclamoService.updateDevolucionReclamo(devolucion);

        assertEquals(1, resultado);
    }

    @Test
    void eliminarDevolucionReclamo() {
        int id = 1;
        when(devolucionReclamoRepository.existsById(id)).thenReturn(true);
        doNothing().when(devolucionReclamoRepository).deleteById(id);

        int resultado = devolucionReclamoService.deleteDevolucionReclamo(id);

        assertEquals(1, resultado);
        verify(devolucionReclamoRepository).deleteById(id);
    }

    private DevolucionReclamo crearDevolucionReclamo() {
        DevolucionReclamo devolucion = new DevolucionReclamo();
        devolucion.setId_devolucion(1);
        devolucion.setVenta(crearVenta());
        devolucion.setCliente(crearCliente());
        devolucion.setProducto(crearProducto());
        devolucion.setMotivo("Producto defectuoso");
        devolucion.setEstado(false);
        devolucion.setFecha(Date.valueOf("2026-12-31"));
        return devolucion;
    }

    private Venta crearVenta() {
        Venta venta = new Venta();
        venta.setId_venta(1);
        return venta;
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

    private Producto crearProducto() {
        Producto producto = new Producto();
        producto.setId_producto(1);
        producto.setNombre("Producto prueba");
        producto.setDescripcion("Descripcion prueba");
        producto.setCategoria("Categoria prueba");
        producto.setPrecio_base(10000);
        producto.setEstado(true);
        return producto;
    }
}

