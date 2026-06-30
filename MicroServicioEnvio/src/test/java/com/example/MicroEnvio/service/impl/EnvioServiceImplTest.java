package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.EnvioDTO;
import com.example.MicroEnvio.entity.Cliente;
import com.example.MicroEnvio.entity.Envio;
import com.example.MicroEnvio.entity.Proveedor;
import com.example.MicroEnvio.entity.RutaEntrega;
import com.example.MicroEnvio.entity.Venta;
import com.example.MicroEnvio.repository.ClienteRepository;
import com.example.MicroEnvio.repository.EnvioRepository;
import com.example.MicroEnvio.repository.ProveedorRepository;
import com.example.MicroEnvio.repository.RutaEntregaRepository;
import com.example.MicroEnvio.repository.VentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnvioServiceImplTest {

    @Mock
    private EnvioRepository envioRepository;
    @Mock
    private VentaRepository ventaRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ProveedorRepository proveedorRepository;
    @Mock
    private RutaEntregaRepository rutaEntregaRepository;

    @InjectMocks
    private EnvioServiceImpl envioService;

    private Envio envio;
    private Venta venta;
    private Cliente cliente;
    private Proveedor proveedor;
    private RutaEntrega ruta;
    private EnvioDTO.Request envioRequest;
    private final Date fechaDespacho = Date.valueOf("2026-06-30");
    private final Date fechaEntregaEst = Date.valueOf("2026-07-03");

    @BeforeEach
    void setUp() {
        venta = new Venta(1, 10, 2, 5, fechaDespacho, 50000, 5000, "boleta");
        cliente = new Cliente(5, "Juan Perez", "juan@correo.com", "987654321", "Av. Siempre Viva 123", 5);
        proveedor = new Proveedor(2, "Transportes Sur");
        ruta = new RutaEntrega(3, "Ruta Zona Sur - Puente Alto");

        envio = new Envio(1, venta, cliente, proveedor, ruta, true, fechaDespacho, fechaEntregaEst);

        envioRequest = new EnvioDTO.Request(1, 5, 2, 3, true, fechaDespacho, fechaEntregaEst);
    }

    @Test
    void RetornarListaDeEnvios() {
        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<EnvioDTO.Response> resultado = envioService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre_cliente());
        verify(envioRepository, times(1)).findAll();
    }

    @Test
    void RetornarSoloEnviosActivos() {
        when(envioRepository.findByEstado(true)).thenReturn(List.of(envio));

        List<EnvioDTO.Response> resultado = envioService.listarActivos();

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).isEstado());
        verify(envioRepository, times(1)).findByEstado(true);
    }

    @Test
    void RetornarEnviosPorClienteId() {
        when(envioRepository.findByClienteId(5)).thenReturn(List.of(envio));

        List<EnvioDTO.Response> resultado = envioService.listarPorCliente(5);

        assertEquals(1, resultado.size());
        verify(envioRepository, times(1)).findByClienteId(5);
    }

    @Test
    void Id_cuandoExisteRetornarEnvio() {
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));

        EnvioDTO.Response resultado = envioService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Transportes Sur", resultado.getNombre_proveedor());
        verify(envioRepository, times(1)).findById(1);
    }

    @Test
    void Id_NoExisteLanzarExcepcion() {
        when(envioRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.buscarPorId(99));
        assertTrue(ex.getMessage().contains("Envío no encontrado"));
    }

    @Test
    void crear_cuandoTodoExisteCrearEnvio() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(clienteRepository.findById(5)).thenReturn(Optional.of(cliente));
        when(proveedorRepository.findById(2)).thenReturn(Optional.of(proveedor));
        when(rutaEntregaRepository.findById(3)).thenReturn(Optional.of(ruta));
        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        EnvioDTO.Response resultado = envioService.crear(envioRequest);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre_cliente());
        verify(envioRepository, times(1)).save(any(Envio.class));
    }

    @Test
    void crear_VentaNoExisteLanzarExcepcion() {
        when(ventaRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.crear(envioRequest));
        assertTrue(ex.getMessage().contains("Venta no encontrada"));
        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    void crear_ClienteNoExisteLanzarExcepcion() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(clienteRepository.findById(5)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.crear(envioRequest));
        assertTrue(ex.getMessage().contains("Cliente no encontrado"));
        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    void crear_ProveedorNoExisteLanzarExcepcion() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(clienteRepository.findById(5)).thenReturn(Optional.of(cliente));
        when(proveedorRepository.findById(2)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.crear(envioRequest));
        assertTrue(ex.getMessage().contains("Proveedor no encontrado"));
        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    void crear_RutaNoExisteLanzarExcepcion() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(clienteRepository.findById(5)).thenReturn(Optional.of(cliente));
        when(proveedorRepository.findById(2)).thenReturn(Optional.of(proveedor));
        when(rutaEntregaRepository.findById(3)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.crear(envioRequest));
        assertTrue(ex.getMessage().contains("Ruta no encontrada"));
        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    void actualizar_ExisteActualizarEnvio() {
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(clienteRepository.findById(5)).thenReturn(Optional.of(cliente));
        when(proveedorRepository.findById(2)).thenReturn(Optional.of(proveedor));
        when(rutaEntregaRepository.findById(3)).thenReturn(Optional.of(ruta));
        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        EnvioDTO.Response resultado = envioService.actualizar(1, envioRequest);

        assertNotNull(resultado);
        verify(envioRepository, times(1)).save(any(Envio.class));
    }

    @Test
    void actualizar_EnvioNoExisteLanzarExcepcion() {
        when(envioRepository.findById(anyInt())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.actualizar(99, envioRequest));
        assertTrue(ex.getMessage().contains("Envío no encontrado"));
        verify(envioRepository, never()).save(any(Envio.class));
    }

    @Test
    void eliminar_ExisteEliminarEnvio() {
        when(envioRepository.existsById(1)).thenReturn(true);

        envioService.eliminar(1);

        verify(envioRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_NoExisteLanzarExcepcion() {
        when(envioRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> envioService.eliminar(99));
        assertTrue(ex.getMessage().contains("Envío no encontrado"));
        verify(envioRepository, never()).deleteById(anyInt());
    }
}
