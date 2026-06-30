package com.example.MicroPostVenta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroPostVenta.entity.Tienda;
import com.example.MicroPostVenta.repository.TiendaRepository;

public class TiendaServiceImplTest {

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaServiceimpl tiendaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTiendas() {
        Tienda tienda = crearTienda();
        when(tiendaRepository.obtenerTienda()).thenReturn(List.of(tienda));

        List<Tienda> resultado = tiendaService.getTiendas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(tienda.getNombre(), resultado.get(0).getNombre());
    }

    @Test
    void buscarTiendaPorId() {
        Tienda tienda = crearTienda();
        when(tiendaRepository.buscarTienda(tienda.getId_tienda())).thenReturn(tienda);

        Tienda resultado = tiendaService.getTienda(tienda.getId_tienda());

        assertNotNull(resultado);
        assertEquals(tienda.getId_tienda(), resultado.getId_tienda());
    }

    @Test
    void guardarTienda() {
        Tienda tienda = crearTienda();
        when(tiendaRepository.save(any(Tienda.class))).thenReturn(tienda);

        Tienda resultado = tiendaService.saveTienda(tienda);

        assertNotNull(resultado);
        assertEquals(tienda.getDireccion(), resultado.getDireccion());
    }

    @Test
    void actualizarTienda() {
        Tienda tienda = crearTienda();
        when(tiendaRepository.buscarTienda(tienda.getId_tienda())).thenReturn(tienda);
        when(tiendaRepository.save(any(Tienda.class))).thenReturn(tienda);

        Tienda resultado = tiendaService.updateTienda(tienda.getId_tienda(), tienda);

        assertNotNull(resultado);
        assertEquals(tienda.getNombre(), resultado.getNombre());
    }

    @Test
    void eliminarTienda() {
        int id = 1;
        when(tiendaRepository.existsById(id)).thenReturn(true);
        doNothing().when(tiendaRepository).deleteById(id);

        int resultado = tiendaService.deleteTienda(id);

        assertEquals(1, resultado);
        verify(tiendaRepository).deleteById(id);
    }

    private Tienda crearTienda() {
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);
        tienda.setNombre("Tienda prueba");
        tienda.setDireccion("Direccion tienda");
        tienda.setComuna(1);
        tienda.setRegion(1);
        return tienda;
    }
}

