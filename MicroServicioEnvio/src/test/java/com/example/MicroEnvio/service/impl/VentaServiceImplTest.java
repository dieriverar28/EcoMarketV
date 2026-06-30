package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.VentaDTO;
import com.example.MicroEnvio.entity.Venta;
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
class VentaServiceImplTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    private Venta venta;
    private VentaDTO.Request ventaRequest;
    private final Date fecha = Date.valueOf("2026-06-30");

    @BeforeEach
    void setUp() {
        venta = new Venta(1, 10, 2, 5, fecha, 50000, 5000, "boleta");
        ventaRequest = new VentaDTO.Request(10, 2, 5, fecha, 50000, 5000, "boleta");
    }

    @Test
    void listar_RetornarListaDeVentas() {
        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        List<VentaDTO.Response> resultado = ventaService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("boleta", resultado.get(0).getTipo_documento());
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void buscaId_ExisteRetornarVenta() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));

        VentaDTO.Response resultado = ventaService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(50000, resultado.getTotal_neto());
        verify(ventaRepository, times(1)).findById(1);
    }

    @Test
    void buscaId_NoExisteLanzarExcepcion() {
        when(ventaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ventaService.buscarPorId(99));
        assertTrue(ex.getMessage().contains("Venta no encontrada"));
    }

    @Test
    void crear_debeCrearVenta() {
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        VentaDTO.Response resultado = ventaService.crear(ventaRequest);

        assertNotNull(resultado);
        assertEquals("boleta", resultado.getTipo_documento());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void actualizar_ExisteActualizarVenta() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        VentaDTO.Response resultado = ventaService.actualizar(1, ventaRequest);

        assertNotNull(resultado);
        assertEquals(50000, resultado.getTotal_neto());
        verify(ventaRepository, times(1)).save(any(Venta.class));
    }

    @Test
    void actualizar_NoExisteLanzarExcepcion() {
        when(ventaRepository.findById(anyInt())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ventaService.actualizar(99, ventaRequest));
        assertTrue(ex.getMessage().contains("Venta no encontrada"));
        verify(ventaRepository, never()).save(any(Venta.class));
    }

    @Test
    void eliminar_ExisteEliminarVenta() {
        when(ventaRepository.existsById(1)).thenReturn(true);

        ventaService.eliminar(1);

        verify(ventaRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_NoExisteLanzarExcepcion() {
        when(ventaRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ventaService.eliminar(99));
        assertTrue(ex.getMessage().contains("Venta no encontrada"));
        verify(ventaRepository, never()).deleteById(anyInt());
    }
}
