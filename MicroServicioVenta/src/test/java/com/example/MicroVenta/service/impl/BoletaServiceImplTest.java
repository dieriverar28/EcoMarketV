package com.example.MicroVenta.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.entity.Boleta;
import com.example.MicroVenta.repository.BoletaRepository;

@ExtendWith(MockitoExtension.class)
class BoletaServiceImplTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaServiceimpl boletaService;

    private Boleta boleta;

    @BeforeEach
    void setUp() {
        boleta = new Boleta();
        boleta.setId_cliente(1);
        boleta.setNombre("Juan Perez");
        boleta.setEmail("juan@email.com");
        boleta.setTelefono("987654321");
        boleta.setComuna("Santiago");
        boleta.setDireccion_envio("Av. Siempre Viva 123");
    }

    @Test
    void getBoletas_deberiaRetornarListaDeBoletas() {
        when(boletaRepository.findAll()).thenReturn(List.of(boleta));

        List<Boleta> resultado = boletaService.getBoletas();

        assertThat(resultado).hasSize(1);
        assertEquals("Juan Perez", resultado.get(0).getNombre());
        assertEquals("juan@email.com", resultado.get(0).getEmail());
        verify(boletaRepository, times(1)).findAll();
    }

    @Test
    void getBoletas_deberiaRetornarListaVacia_siNoHayBoletas() {
        when(boletaRepository.findAll()).thenReturn(List.of());

        List<Boleta> resultado = boletaService.getBoletas();

        assertThat(resultado).isEmpty();
    }

    @Test
    void saveBoletas_deberiaGuardarYRetornarBoleta() {
        when(boletaRepository.save(any(Boleta.class))).thenReturn(boleta);

        Boleta resultado = boletaService.saveBoletas(boleta);

        assertEquals(1, resultado.getId_cliente());
        assertEquals("Juan Perez", resultado.getNombre());
        verify(boletaRepository).save(any(Boleta.class));
    }

    @Test
    void getBoletaById_deberiaRetornarBoleta_siExiste() {
        when(boletaRepository.findById(1)).thenReturn(Optional.of(boleta));

        Boleta resultado = boletaService.getBoletaById(1);

        assertEquals(1, resultado.getId_cliente());
        assertEquals("Juan Perez", resultado.getNombre());
    }

    @Test
    void getBoletaById_deberiaLanzarExcepcion_siNoExiste() {
        when(boletaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> boletaService.getBoletaById(99));

        assertThat(ex.getMessage()).contains("Boleta no encontrada con id: 99");
    }

    @Test
    void deleteBoleta_deberiaEliminarBoleta_siExiste() {
        when(boletaRepository.existsById(1)).thenReturn(true);
        doNothing().when(boletaRepository).deleteById(1);

        int resultado = boletaService.deleteBoleta(1);

        assertEquals(1, resultado);
        verify(boletaRepository).existsById(1);
        verify(boletaRepository).deleteById(1);
    }

    @Test
    void deleteBoleta_deberiaRetornarCero_siNoExiste() {
        when(boletaRepository.existsById(99)).thenReturn(false);

        int resultado = boletaService.deleteBoleta(99);

        assertEquals(0, resultado);
        verify(boletaRepository).existsById(99);
        verify(boletaRepository, never()).deleteById(any(Integer.class));
    }
}
