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

import com.example.MicroVenta.entity.CuponDescuento;
import com.example.MicroVenta.repository.CuponDescuentoRepository;

@ExtendWith(MockitoExtension.class)
class CuponDescuentoServiceImplTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoServiceimpl cuponDescuentoService;

    private CuponDescuento cupon;

    @BeforeEach
    void setUp() {
        cupon = new CuponDescuento();
        cupon.setId_cupon_descuento(1);
        cupon.setCodigo(1010);
        cupon.setDescuento_pct(10);
        cupon.setDescuento_monto(1000);
        cupon.setFecha_expiracion(Date.valueOf("2025-12-31"));
        cupon.setActivo(true);
    }

    @Test
    void listarCupones() {
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> resultado = cuponDescuentoService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getCodigo()).isEqualTo(1010);
        verify(cuponDescuentoRepository, times(1)).findAll();
    }

    @Test
    void buscarCuponPorId() {
        when(cuponDescuentoRepository.findById(1)).thenReturn(Optional.of(cupon));

        CuponDescuento resultado = cuponDescuentoService.buscarPorId(1);

        assertThat(resultado.getCodigo()).isEqualTo(1010);
        assertThat(resultado.isActivo()).isTrue();
    }

    @Test
    void buscarCuponPorIdNoExiste() {
        when(cuponDescuentoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> cuponDescuentoService.buscarPorId(99));

        assertThat(ex.getMessage()).contains("Cupón descuento no encontrado con id: 99");
    }

    @Test
    void crearCupon() {
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cupon);

        CuponDescuento resultado = cuponDescuentoService.crear(cupon);

        assertThat(resultado.getCodigo()).isEqualTo(1010);
        verify(cuponDescuentoRepository).save(any(CuponDescuento.class));
    }

    @Test
    void actualizarCupon() {
        when(cuponDescuentoRepository.findById(1)).thenReturn(Optional.of(cupon));
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cupon);

        CuponDescuento resultado = cuponDescuentoService.actualizar(1, cupon);

        assertThat(resultado.getCodigo()).isEqualTo(1010);
        verify(cuponDescuentoRepository).save(any(CuponDescuento.class));
    }

    @Test
    void eliminarCupon() {
        when(cuponDescuentoRepository.existsById(1)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(1);

        cuponDescuentoService.eliminar(1);

        verify(cuponDescuentoRepository).existsById(1);
        verify(cuponDescuentoRepository).deleteById(1);
    }

    @Test
    void eliminarCuponNoExiste() {
        when(cuponDescuentoRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> cuponDescuentoService.eliminar(99));

        assertThat(ex.getMessage()).contains("Cupón descuento no encontrado con id: 99");
    }

    @Test
    void getAllCupones() {
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> resultado = cuponDescuentoService.getAllCupones();

        assertThat(resultado).hasSize(1);
        verify(cuponDescuentoRepository).findAll();
    }

    @Test
    void saveCuponDescuento() {
        when(cuponDescuentoRepository.save(any(CuponDescuento.class))).thenReturn(cupon);

        CuponDescuento resultado = cuponDescuentoService.saveCuponDescuento(cupon);

        assertThat(resultado.getId_cupon_descuento()).isEqualTo(1);
        verify(cuponDescuentoRepository).save(any(CuponDescuento.class));
    }

    @Test
    void getCuponById() {
        when(cuponDescuentoRepository.findById(1)).thenReturn(Optional.of(cupon));

        CuponDescuento resultado = cuponDescuentoService.getCuponDescuentoById(1);

        assertThat(resultado.getCodigo()).isEqualTo(1010);
    }

    @Test
    void getCuponByIdNoExiste() {
        when(cuponDescuentoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> cuponDescuentoService.getCuponDescuentoById(99));

        assertThat(ex.getMessage()).contains("Cupón descuento no encontrado con id: 99");
    }

    @Test
    void deleteCupon() {
        when(cuponDescuentoRepository.existsById(1)).thenReturn(true);
        doNothing().when(cuponDescuentoRepository).deleteById(1);

        int resultado = cuponDescuentoService.deleteCuponDescuento(1);

        assertThat(resultado).isEqualTo(1);
        verify(cuponDescuentoRepository).deleteById(1);
    }

    @Test
    void deleteCuponNoExiste() {
        when(cuponDescuentoRepository.existsById(99)).thenReturn(false);

        int resultado = cuponDescuentoService.deleteCuponDescuento(99);

        assertThat(resultado).isZero();
        verify(cuponDescuentoRepository, never()).deleteById(any(Integer.class));
    }
}
