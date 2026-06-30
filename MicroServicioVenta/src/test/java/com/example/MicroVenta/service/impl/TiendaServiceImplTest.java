package com.example.MicroVenta.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.example.MicroVenta.entity.Tienda;
import com.example.MicroVenta.repository.TiendaRepository;

@ExtendWith(MockitoExtension.class)
class TiendaServiceImplTest {

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaServiceimpl tiendaService;

    private Tienda tienda;

    @BeforeEach
    void setUp() {
        tienda = new Tienda();
        tienda.setId_tienda(1);
        tienda.setNombre("Tienda Centro");
        tienda.setDireccion("Av. Principal 123");
        tienda.setComuna(1);
        tienda.setRegion(2);
    }

    @Test
    void listarTodos_deberiaRetornarListaDeTiendas() {
        when(tiendaRepository.findAll()).thenReturn(List.of(tienda));

        List<Tienda> resultado = tiendaService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Tienda Centro");
        verify(tiendaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_deberiaRetornarTienda_siExiste() {
        when(tiendaRepository.findById(1)).thenReturn(Optional.of(tienda));

        Tienda resultado = tiendaService.buscarPorId(1);

        assertThat(resultado.getNombre()).isEqualTo("Tienda Centro");
        assertThat(resultado.getRegion()).isEqualTo(2);
    }

    @Test
    void buscarPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(tiendaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tiendaService.buscarPorId(99));

        assertThat(ex.getMessage()).contains("Tienda no encontrada con id: 99");
    }

    @Test
    void crear_deberiaGuardarYRetornarTienda() {
        when(tiendaRepository.save(any(Tienda.class))).thenReturn(tienda);

        Tienda resultado = tiendaService.crear(tienda);

        assertThat(resultado.getNombre()).isEqualTo("Tienda Centro");
        verify(tiendaRepository).save(any(Tienda.class));
    }

    @Test
    void actualizar_deberiaModificarYGuardarTienda() {
        when(tiendaRepository.findById(1)).thenReturn(Optional.of(tienda));
        when(tiendaRepository.save(any(Tienda.class))).thenReturn(tienda);

        Tienda resultado = tiendaService.actualizar(1, tienda);

        assertThat(resultado.getNombre()).isEqualTo("Tienda Centro");
        verify(tiendaRepository).save(any(Tienda.class));
    }

    @Test
    void eliminar_deberiaEliminarTienda_siExiste() {
        when(tiendaRepository.existsById(1)).thenReturn(true);
        doNothing().when(tiendaRepository).deleteById(1);

        tiendaService.eliminar(1);

        verify(tiendaRepository).existsById(1);
        verify(tiendaRepository).deleteById(1);
    }

    @Test
    void eliminar_deberiaLanzarExcepcion_siNoExiste() {
        when(tiendaRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tiendaService.eliminar(99));

        assertThat(ex.getMessage()).contains("Tienda no encontrada con id: 99");
        verify(tiendaRepository, never()).deleteById(any(Integer.class));
    }
}
