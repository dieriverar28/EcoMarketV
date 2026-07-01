package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.RutaEntregaDTO;
import com.example.MicroEnvio.entity.RutaEntrega;
import com.example.MicroEnvio.repository.RutaEntregaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RutaServiceImplTest {

    @Mock
    private RutaEntregaRepository rutaEntregaRepository;

    @InjectMocks
    private RutaEntregaServiceImpl rutaEntregaService;

    private RutaEntrega ruta;
    private RutaEntregaDTO.Request rutaRequest;

    @BeforeEach
    void setUp() {
        ruta = new RutaEntrega(1, "Ruta Zona Sur - Puente Alto");
        rutaRequest = new RutaEntregaDTO.Request("Ruta Zona Sur - Puente Alto");
    }

    @Test
    void listar_RetornarListaDeRutas() {
        when(rutaEntregaRepository.findAll()).thenReturn(List.of(ruta));

        List<RutaEntregaDTO.Response> resultado = rutaEntregaService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Ruta Zona Sur - Puente Alto", resultado.get(0).getDescripcion());
        verify(rutaEntregaRepository, times(1)).findAll();
    }

    @Test
    void buscaId_ExisteRetornarRuta() {
        when(rutaEntregaRepository.findById(1)).thenReturn(Optional.of(ruta));

        RutaEntregaDTO.Response resultado = rutaEntregaService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Ruta Zona Sur - Puente Alto", resultado.getDescripcion());
        verify(rutaEntregaRepository, times(1)).findById(1);
    }

    @Test
    void buscaId_NoExisteLanzarExcepcion() {
        when(rutaEntregaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> rutaEntregaService.buscarPorId(99));
        assertTrue(ex.getMessage().contains("Ruta de entrega no encontrada"));
    }

    @Test
    void crear_debeCrearRuta() {
        when(rutaEntregaRepository.save(any(RutaEntrega.class))).thenReturn(ruta);

        RutaEntregaDTO.Response resultado = rutaEntregaService.crear(rutaRequest);

        assertNotNull(resultado);
        assertEquals("Ruta Zona Sur - Puente Alto", resultado.getDescripcion());
        verify(rutaEntregaRepository, times(1)).save(any(RutaEntrega.class));
    }

    @Test
    void actualizar_ExisteActualizarRuta() {
        when(rutaEntregaRepository.findById(1)).thenReturn(Optional.of(ruta));
        when(rutaEntregaRepository.save(any(RutaEntrega.class))).thenReturn(ruta);

        RutaEntregaDTO.Response resultado = rutaEntregaService.actualizar(1, rutaRequest);

        assertNotNull(resultado);
        assertEquals("Ruta Zona Sur - Puente Alto", resultado.getDescripcion());
        verify(rutaEntregaRepository, times(1)).save(any(RutaEntrega.class));
    }

    @Test
    void actualizar_NoExisteLanzarExcepcion() {
        when(rutaEntregaRepository.findById(anyInt())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> rutaEntregaService.actualizar(99, rutaRequest));
        assertTrue(ex.getMessage().contains("Ruta de entrega no encontrada"));
        verify(rutaEntregaRepository, never()).save(any(RutaEntrega.class));
    }

    @Test
    void eliminar_ExisteEliminarRuta() {
        when(rutaEntregaRepository.existsById(1)).thenReturn(true);

        rutaEntregaService.eliminar(1);

        verify(rutaEntregaRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_NoExisteLanzarExcepcion() {
        when(rutaEntregaRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> rutaEntregaService.eliminar(99));
        assertTrue(ex.getMessage().contains("Ruta de entrega no encontrada"));
        verify(rutaEntregaRepository, never()).deleteById(anyInt());
    }
}
