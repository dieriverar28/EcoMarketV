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

import com.example.MicroVenta.dto.VentaDTO;
import com.example.MicroVenta.entity.Venta;
import com.example.MicroVenta.repository.VentaRepository;

@ExtendWith(MockitoExtension.class)
class VentaServiceImplTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    private Venta venta;
    private VentaDTO.Request request;

    @BeforeEach
    void setUp() {
        venta = new Venta();
        venta.setId_venta(1);
        venta.setFecha_venta(Date.valueOf("2024-01-10"));
        venta.setTotal_neto(10000);
        venta.setDescuento_aplicado(1000);
        venta.setTipo_documento("Boleta");

        request = new VentaDTO.Request();
        request.setFecha_venta(Date.valueOf("2024-02-10"));
        request.setTotal_neto(20000);
        request.setDescuento_aplicado(2000);
        request.setTipo_documento("Factura");
    }

    @Test
    void listarTodos_deberiaRetornarListaDeVentas() {
        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        List<VentaDTO.Response> resultado = ventaService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getId_venta()).isEqualTo(1);
        assertThat(resultado.get(0).getTotal_neto()).isEqualTo(10000);
        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_deberiaRetornarVenta_siExiste() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));

        VentaDTO.Response resultado = ventaService.buscarPorId(1);

        assertThat(resultado.getId_venta()).isEqualTo(1);
        assertThat(resultado.getTotal_neto()).isEqualTo(10000);
    }

    @Test
    void buscarPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(ventaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ventaService.buscarPorId(99));

        assertThat(ex.getMessage()).contains("Venta no encontrada con id: 99");
    }

    @Test
    void crear_deberiaGuardarYRetornarVenta() {
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        VentaDTO.Response resultado = ventaService.crear(request);

        assertThat(resultado.getId_venta()).isEqualTo(1);
        assertThat(resultado.getTotal_neto()).isEqualTo(10000);
        verify(ventaRepository).save(any(Venta.class));
    }

    @Test
    void actualizar_deberiaModificarYGuardarVenta() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);

        VentaDTO.Response resultado = ventaService.actualizar(1, request);

        assertThat(resultado.getId_venta()).isEqualTo(1);
        verify(ventaRepository).save(any(Venta.class));
    }

    @Test
    void eliminar_deberiaEliminarVenta_siExiste() {
        when(ventaRepository.existsById(1)).thenReturn(true);
        doNothing().when(ventaRepository).deleteById(1);

        ventaService.eliminar(1);

        verify(ventaRepository).existsById(1);
        verify(ventaRepository).deleteById(1);
    }

    @Test
    void eliminar_deberiaLanzarExcepcion_siNoExiste() {
        when(ventaRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ventaService.eliminar(99));

        assertThat(ex.getMessage()).contains("Venta no encontrada con id: 99");
        verify(ventaRepository, never()).deleteById(any(Integer.class));
    }
}
