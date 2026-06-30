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

import com.example.MicroPostVenta.entity.CuponDescuento;
import com.example.MicroPostVenta.repository.CuponDescuentoRepository;

public class CuponDescuentoServiceImplTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoServiceimpl cuponDescuentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarCuponesDescuento() {
        CuponDescuento cupon = crearCuponDescuento();
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> resultado = cuponDescuentoService.obtenerCuponDescuentos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(cupon.getCodigo(), resultado.get(0).getCodigo());
    }

    @Test
    void buscarCuponDescuentoPorId() {
        CuponDescuento cupon = crearCuponDescuento();
        when(cuponDescuentoRepository.findById(cupon.getId_cupon_descuento())).thenReturn(Optional.of(cupon));

        CuponDescuento resultado = cuponDescuentoService.buscarCuponDescuento(cupon.getId_cupon_descuento());

        assertNotNull(resultado);
        assertEquals(cupon.getId_cupon_descuento(), resultado.getId_cupon_descuento());
        assertEquals(cupon.getCodigo(), resultado.getCodigo());
    }

    @Test
    void guardarCuponDescuento() {
        CuponDescuento cupon = crearCuponDescuento();
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cupon);

        CuponDescuento resultado = cuponDescuentoService.crearCuponDescuento(cupon);

        assertNotNull(resultado);
        assertEquals(cupon.getCodigo(), resultado.getCodigo());
        assertEquals(cupon.getDescuento_pct(), resultado.getDescuento_pct());
    }

    @Test
    void actualizarCuponDescuento() {
        CuponDescuento cupon = crearCuponDescuento();
        cupon.setDescuento_pct(30);
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cupon);

        CuponDescuento resultado = cuponDescuentoService.actualizarCuponDescuento(cupon);

        assertNotNull(resultado);
        assertEquals(30, resultado.getDescuento_pct());
    }

    @Test
    void eliminarCuponDescuento() {
        int id = 1;
        when(cuponDescuentoRepository.existsById(id)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(id);

        int resultado = cuponDescuentoService.eliminarCuponDescuento(id);

        assertEquals(1, resultado);
        verify(cuponDescuentoRepository).deleteById(id);
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

