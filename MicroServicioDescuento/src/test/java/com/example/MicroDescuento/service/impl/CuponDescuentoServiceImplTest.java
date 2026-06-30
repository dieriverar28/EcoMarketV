package com.example.MicroDescuento.service.impl;

import com.example.MicroDescuento.dto.CuponDescuentoDTO;
import com.example.MicroDescuento.entity.CuponDescuento;
import com.example.MicroDescuento.repository.CuponDescuentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuponDescuentoServiceImplTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoServiceImpl cuponDescuentoService;

    private CuponDescuento cupon;
    private CuponDescuentoDTO.Response cuponResponse;

    @BeforeEach
    void setUp() {
        cupon = new CuponDescuento();
        cupon.setId_cupon_descuento(1);
        cupon.setCodigo(12345);
        cupon.setDescuento_pct(10);
        cupon.setDescuento_monto(0);
        cupon.setFecha_expiracion(Date.valueOf("2025-12-31"));
        cupon.setActivo(true);

        cuponResponse = new CuponDescuentoDTO.Response(
                1,
                12345,
                10,
                0,
                Date.valueOf("2025-12-31"),
                true
        );
    }

    // ---------- getAllCupones ----------

    @Test
    void getAllCupones_deberiaRetornarListaDeCupones() {
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuentoDTO.Response> resultado = cuponDescuentoService.getAllCupones();

        assertThat(resultado).hasSize(1);
        assertEquals(12345, resultado.get(0).getCodigo());
        assertEquals(10, resultado.get(0).getDescuento_pct());
        verify(cuponDescuentoRepository, times(1)).findAll();
    }

    @Test
    void getAllCupones_deberiaRetornarListaVacia_siNoHayCupones() {
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of());

        List<CuponDescuentoDTO.Response> resultado = cuponDescuentoService.getAllCupones();

        assertThat(resultado).isEmpty();
    }

    // ---------- getCuponDescuentoById ----------

    @Test
    void getCuponDescuentoById_deberiaRetornarCupon_siExiste() {
        when(cuponDescuentoRepository.findById(1)).thenReturn(Optional.of(cupon));

        CuponDescuentoDTO.Response resultado = cuponDescuentoService.getCuponDescuentoById(1);

        assertEquals(1, resultado.getId_cupon_descuento());
        assertEquals(12345, resultado.getCodigo());
        assertEquals(10, resultado.getDescuento_pct());
    }

    @Test
    void getCuponDescuentoById_deberiaLanzarExcepcion_siNoExiste() {
        when(cuponDescuentoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cuponDescuentoService.getCuponDescuentoById(99));

        assertThat(ex.getMessage()).contains("Cupon de descuento no encontrado con id: 99");
    }

    // ---------- saveCuponDescuento ----------

    @Test
    void saveCuponDescuento_deberiaGuardarYRetornarCupon_siDatosValidos() {
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cupon);

        CuponDescuentoDTO.Response resultado = cuponDescuentoService.saveCuponDescuento(cupon);

        assertEquals(12345, resultado.getCodigo());
        assertEquals(10, resultado.getDescuento_pct());
        assertTrue(resultado.isActivo());
        verify(cuponDescuentoRepository).save(any(CuponDescuento.class));
    }

    @Test
    void saveCuponDescuento_deberiaMapearCorrectamenteLaRespuesta() {
        CuponDescuento nuevosCupon = new CuponDescuento();
        nuevosCupon.setId_cupon_descuento(2);
        nuevosCupon.setCodigo(54321);
        nuevosCupon.setDescuento_pct(15);
        nuevosCupon.setDescuento_monto(5000);
        nuevosCupon.setFecha_expiracion(Date.valueOf("2026-06-30"));
        nuevosCupon.setActivo(true);

        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(nuevosCupon);

        CuponDescuentoDTO.Response resultado = cuponDescuentoService.saveCuponDescuento(nuevosCupon);

        assertEquals(2, resultado.getId_cupon_descuento());
        assertEquals(54321, resultado.getCodigo());
        assertEquals(15, resultado.getDescuento_pct());
        assertEquals(5000, resultado.getDescuento_monto());
    }

    // ---------- updateCuponDescuento ----------

    @Test
    void updateCuponDescuento_deberiaActualizarYRetornar1_siExiste() {
        CuponDescuento cuponActualizado = new CuponDescuento();
        cuponActualizado.setId_cupon_descuento(1);
        cuponActualizado.setCodigo(99999);
        cuponActualizado.setDescuento_pct(20);
        cuponActualizado.setDescuento_monto(0);
        cuponActualizado.setFecha_expiracion(Date.valueOf("2025-12-31"));
        cuponActualizado.setActivo(true);

        when(cuponDescuentoRepository.existsById(1)).thenReturn(true);
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cuponActualizado);

        int resultado = cuponDescuentoService.updateCuponDescuento(cuponActualizado);

        assertEquals(1, resultado);
        verify(cuponDescuentoRepository).existsById(1);
        verify(cuponDescuentoRepository).save(cuponActualizado);
    }

    @Test
    void updateCuponDescuento_deberiaLanzarExcepcion_siCuponNoExiste() {
        when(cuponDescuentoRepository.existsById(99)).thenReturn(false);

        CuponDescuento cuponNoExistente = new CuponDescuento();
        cuponNoExistente.setId_cupon_descuento(99);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cuponDescuentoService.updateCuponDescuento(cuponNoExistente));

        assertThat(ex.getMessage()).contains("Cupon de descuento no encontrado con id: 99");
        verify(cuponDescuentoRepository, never()).save(any());
    }

    // ---------- deleteCuponDescuento ----------

    @Test
    void deleteCuponDescuento_deberiaEliminarYRetornar1_siExiste() {
        when(cuponDescuentoRepository.existsById(1)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(1);

        int resultado = cuponDescuentoService.deleteCuponDescuento(1);

        assertEquals(1, resultado);
        verify(cuponDescuentoRepository).existsById(1);
        verify(cuponDescuentoRepository).deleteById(1);
    }

    @Test
    void deleteCuponDescuento_deberiaLanzarExcepcion_siCuponNoExiste() {
        when(cuponDescuentoRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> cuponDescuentoService.deleteCuponDescuento(99));

        assertThat(ex.getMessage()).contains("Cupon de descuento no encontrado con id: 99");
        verify(cuponDescuentoRepository, never()).deleteById(anyInt());
    }

    @Test
    void deleteCuponDescuento_deberiaVerificarExistenciaAntesDeBorrar() {
        when(cuponDescuentoRepository.existsById(1)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(1);

        cuponDescuentoService.deleteCuponDescuento(1);

        verify(cuponDescuentoRepository).existsById(1);
        verify(cuponDescuentoRepository).deleteById(1);
    }
}
