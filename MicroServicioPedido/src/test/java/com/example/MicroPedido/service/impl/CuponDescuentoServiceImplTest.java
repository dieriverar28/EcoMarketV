package com.example.MicroPedido.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.entity.CuponDescuento;
import com.example.MicroPedido.repository.CuponDescuentoRepository;

@ExtendWith(MockitoExtension.class)
class CuponDescuentoServiceImplTest {

    @Mock
    private CuponDescuentoRepository cuponDescuentoRepository;

    @InjectMocks
    private CuponDescuentoServiceImpl cuponDescuentoService;

    @Test
    void listarCupones() {
        CuponDescuento cupon = new CuponDescuento(1, 1234, 15, 5000, new Date(), true);
        when(cuponDescuentoRepository.findAll()).thenReturn(List.of(cupon));

        List<CuponDescuento> result = cuponDescuentoService.getAllCupones();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCodigo()).isEqualTo(1234);
    }

    @Test
    void buscarCuponPorId() {
        CuponDescuento cupon = new CuponDescuento(2, 5678, 20, 7000, new Date(), false);
        when(cuponDescuentoRepository.buscarCuponDescuento(2)).thenReturn(cupon);

        CuponDescuento result = cuponDescuentoService.getCuponDescuentoById(2);

        assertThat(result.getId_cupon()).isEqualTo(2);
        assertThat(result.isActivo()).isFalse();
    }

    @Test
    void guardarCupon() {
        CuponDescuento cupon = new CuponDescuento(3, 9999, 10, 3000, new Date(), true);
        when(cuponDescuentoRepository.save(cupon)).thenReturn(cupon);

        CuponDescuento result = cuponDescuentoService.saveCuponDescuento(cupon);

        assertThat(result).isEqualTo(cupon);
        verify(cuponDescuentoRepository).save(cupon);
    }

    @Test
    void actualizarCupon() {
        CuponDescuento cupon = new CuponDescuento(4, 1111, 5, 1000, new Date(), true);

        int result = cuponDescuentoService.updateCuponDescuento(cupon);

        assertThat(result).isEqualTo(1);
        verify(cuponDescuentoRepository).save(cupon);
    }

    @Test
    void eliminarCupon() {
        CuponDescuento cupon = new CuponDescuento(5, 2222, 25, 4000, new Date(), true);
        when(cuponDescuentoRepository.buscarCuponDescuento(5)).thenReturn(cupon);

        int result = cuponDescuentoService.deleteCuponDescuento(5);

        assertThat(result).isEqualTo(1);
        verify(cuponDescuentoRepository).delete(cupon);
    }
}
