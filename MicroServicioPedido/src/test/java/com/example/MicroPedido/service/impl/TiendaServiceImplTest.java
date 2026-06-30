package com.example.MicroPedido.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.dto.TiendaDTO;
import com.example.MicroPedido.entity.Tienda;
import com.example.MicroPedido.repository.TiendaRepository;

@ExtendWith(MockitoExtension.class)
class TiendaServiceImplTest {

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaServiceImpl tiendaService;

    @Test
    void listarTiendas() {
        Tienda tienda = new Tienda(1, "Central", "Av. Principal", 101);
        when(tiendaRepository.obtenerTiendas()).thenReturn(List.of(tienda));

        List<TiendaDTO.Response> result = tiendaService.listarTiendas();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Central");
    }

    @Test
    void buscarTiendaPorId() {
        Tienda tienda = new Tienda(2, "Norte", "Calle 2", 202);
        when(tiendaRepository.buscarTienda(2)).thenReturn(tienda);

        TiendaDTO.Response result = tiendaService.buscarTienda(2);

        assertThat(result.getId_tienda()).isEqualTo(2);
        assertThat(result.getDireccion()).isEqualTo("Calle 2");
    }

    @Test
    void guardarTienda() {
        TiendaDTO.Request request = new TiendaDTO.Request("Sur", "Calle 3", 303);
        Tienda tiendaGuardada = new Tienda(3, "Sur", "Calle 3", 303);
        when(tiendaRepository.save(org.mockito.ArgumentMatchers.any(Tienda.class))).thenReturn(tiendaGuardada);

        TiendaDTO.Response result = tiendaService.guardarTienda(request);

        assertThat(result.getNombre()).isEqualTo("Sur");
        assertThat(result.getId_comuna()).isEqualTo(303);
    }

    @Test
    void actualizarTienda() {
        TiendaDTO.Request request = new TiendaDTO.Request("Este", "Av. Este", 404);
        Tienda tienda = new Tienda(4, "Vieja", "Antigua", 100);
        when(tiendaRepository.buscarTienda(4)).thenReturn(tienda);
        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        TiendaDTO.Response result = tiendaService.actualizarTienda(4, request);

        assertThat(result.getNombre()).isEqualTo("Este");
        assertThat(result.getDireccion()).isEqualTo("Av. Este");
        verify(tiendaRepository).save(tienda);
    }

    @Test
    void eliminarTienda() {
        int result = tiendaService.eliminarTienda(5);

        assertThat(result).isEqualTo(1);
        verify(tiendaRepository).deleteById(5);
    }
}
