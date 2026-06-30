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

import com.example.MicroVenta.entity.Factura;
import com.example.MicroVenta.entity.Venta;
import com.example.MicroVenta.repository.FacturaRepository;

@ExtendWith(MockitoExtension.class)
class FacturaServiceImplTest {

    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private FacturaServiceimpl facturaService;

    private Factura factura;

    @BeforeEach
    void setUp() {
        factura = new Factura();
        factura.setId_factura(1);
        factura.setVenta(new Venta());
        factura.setFolio("F001");
        factura.setTimbre_electronico("TIMBRE-001");
        factura.setRazon_social("EcoMarket S.A.");
        factura.setNumrun_cliente("12345678-9");
        factura.setDvrun_cliente("9");
        factura.setGiro("Venta de productos");
        factura.setFecha_emision(Date.valueOf("2024-01-15"));
        factura.setMonto_iva(1900);
        factura.setMonto_total(11900);
        factura.setEmail_envio("factura@ecomarket.cl");
    }

    @Test
    void listarTodos_deberiaRetornarListaDeFacturas() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        List<Factura> resultado = facturaService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getFolio()).isEqualTo("F001");
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_deberiaRetornarFactura_siExiste() {
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));

        Factura resultado = facturaService.buscarPorId(1);

        assertThat(resultado.getFolio()).isEqualTo("F001");
        assertThat(resultado.getEmail_envio()).isEqualTo("factura@ecomarket.cl");
    }

    @Test
    void buscarPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(facturaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> facturaService.buscarPorId(99));

        assertThat(ex.getMessage()).contains("Factura no encontrada con id: 99");
    }

    @Test
    void crear_deberiaGuardarYRetornarFactura() {
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        Factura resultado = facturaService.crear(factura);

        assertThat(resultado.getFolio()).isEqualTo("F001");
        verify(facturaRepository).save(any(Factura.class));
    }

    @Test
    void actualizar_deberiaModificarYGuardarFactura() {
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        Factura resultado = facturaService.actualizar(1, factura);

        assertThat(resultado.getFolio()).isEqualTo("F001");
        verify(facturaRepository).save(any(Factura.class));
    }

    @Test
    void eliminar_deberiaEliminarFactura_siExiste() {
        when(facturaRepository.existsById(1)).thenReturn(true);
        doNothing().when(facturaRepository).deleteById(1);

        facturaService.eliminar(1);

        verify(facturaRepository).existsById(1);
        verify(facturaRepository).deleteById(1);
    }

    @Test
    void eliminar_deberiaLanzarExcepcion_siNoExiste() {
        when(facturaRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> facturaService.eliminar(99));

        assertThat(ex.getMessage()).contains("Factura no encontrada con id: 99");
    }

    @Test
    void getFacturas_deberiaRetornarTodasLasFacturas() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        List<Factura> resultado = facturaService.getFacturas();

        assertThat(resultado).hasSize(1);
        verify(facturaRepository).findAll();
    }

    @Test
    void saveFactura_deberiaGuardarYRetornarId() {
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        int resultado = facturaService.saveFactura(factura);

        assertThat(resultado).isEqualTo(1);
        verify(facturaRepository).save(any(Factura.class));
    }

    @Test
    void getFactura_deberiaRetornarFactura_siExiste() {
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));

        Factura resultado = facturaService.getFactura(1);

        assertThat(resultado.getFolio()).isEqualTo("F001");
    }

    @Test
    void getFactura_deberiaLanzarExcepcion_siNoExiste() {
        when(facturaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> facturaService.getFactura(99));

        assertThat(ex.getMessage()).contains("Factura no encontrada con id: 99");
    }

    @Test
    void deleteFactura_deberiaEliminarFactura_siExiste() {
        when(facturaRepository.existsById(1)).thenReturn(true);
        doNothing().when(facturaRepository).deleteById(1);

        int resultado = facturaService.deleteFactura(1);

        assertThat(resultado).isEqualTo(1);
        verify(facturaRepository).deleteById(1);
    }

    @Test
    void deleteFactura_deberiaRetornarCero_siNoExiste() {
        when(facturaRepository.existsById(99)).thenReturn(false);

        int resultado = facturaService.deleteFactura(99);

        assertThat(resultado).isZero();
        verify(facturaRepository, never()).deleteById(any(Integer.class));
    }
}
