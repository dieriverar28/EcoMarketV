package com.example.MicroCliente.service.impl;

import com.example.MicroCliente.dto.GeneroDTO;
import com.example.MicroCliente.entity.Genero;
import com.example.MicroCliente.repository.GeneroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeneroServiceImplTest {

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private GeneroServiceImpl generoService;

    private Genero genero;
    private GeneroDTO.Request generoRequest;
    private GeneroDTO.Response generoResponse;

    @BeforeEach
    void setUp() {
        genero = new Genero();
        genero.setId_genero(1);
        genero.setNombre_genero("Masculino");

        generoRequest = new GeneroDTO.Request();
        generoRequest.setNombre_genero("Masculino");

        generoResponse = new GeneroDTO.Response(1, "Masculino");
    }

    // ---------- listarGeneros ----------

    @Test
    void listarGeneros_deberiaRetornarListaDeGeneros() {
        when(generoRepository.findAll()).thenReturn(List.of(genero));

        List<GeneroDTO.Response> resultado = generoService.listarGeneros();

        assertThat(resultado).hasSize(1);
        assertEquals("Masculino", resultado.get(0).getNombre_genero());
        assertEquals(1, resultado.get(0).getId_genero());
        verify(generoRepository, times(1)).findAll();
    }

    @Test
    void listarGeneros_deberiaRetornarListaVacia_siNoHayGeneros() {
        when(generoRepository.findAll()).thenReturn(List.of());

        List<GeneroDTO.Response> resultado = generoService.listarGeneros();

        assertThat(resultado).isEmpty();
    }

    @Test
    void listarGeneros_deberiaMapearCorrectamente() {
        Genero genero2 = new Genero();
        genero2.setId_genero(2);
        genero2.setNombre_genero("Femenino");

        when(generoRepository.findAll()).thenReturn(List.of(genero, genero2));

        List<GeneroDTO.Response> resultado = generoService.listarGeneros();

        assertThat(resultado).hasSize(2);
        assertEquals("Masculino", resultado.get(0).getNombre_genero());
        assertEquals("Femenino", resultado.get(1).getNombre_genero());
    }

    // ---------- obtenerGeneroPorId ----------

    @Test
    void obtenerGeneroPorId_deberiaRetornarGenero_siExiste() {
        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));

        GeneroDTO.Response resultado = generoService.obtenerGeneroPorId(1);

        assertEquals(1, resultado.getId_genero());
        assertEquals("Masculino", resultado.getNombre_genero());
    }

    @Test
    void obtenerGeneroPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(generoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> generoService.obtenerGeneroPorId(99));

        assertThat(ex.getMessage()).contains("Género no encontrado con ID: 99");
    }

    // ---------- crearGenero ----------

    @Test
    void crearGenero_deberiaGuardarYRetornarGenero_siDatosValidos() {
        when(generoRepository.save(any(Genero.class))).thenReturn(genero);

        GeneroDTO.Response resultado = generoService.crearGenero(generoRequest);

        assertEquals("Masculino", resultado.getNombre_genero());
        assertEquals(1, resultado.getId_genero());
        verify(generoRepository).save(any(Genero.class));
    }

    @Test
    void crearGenero_deberiaMapearCorrectamenteLaRespuesta() {
        Genero nuevoGenero = new Genero();
        nuevoGenero.setId_genero(2);
        nuevoGenero.setNombre_genero("Femenino");

        GeneroDTO.Request request = new GeneroDTO.Request();
        request.setNombre_genero("Femenino");

        when(generoRepository.save(any(Genero.class))).thenReturn(nuevoGenero);

        GeneroDTO.Response resultado = generoService.crearGenero(request);

        assertEquals(2, resultado.getId_genero());
        assertEquals("Femenino", resultado.getNombre_genero());
    }

    @Test
    void crearGenero_deberiaCrearGeneroPorIddatosValidos() {
        Genero generoOtro = new Genero();
        generoOtro.setId_genero(3);
        generoOtro.setNombre_genero("Otro");

        GeneroDTO.Request request = new GeneroDTO.Request();
        request.setNombre_genero("Otro");

        when(generoRepository.save(any(Genero.class))).thenReturn(generoOtro);

        GeneroDTO.Response resultado = generoService.crearGenero(request);

        assertEquals(3, resultado.getId_genero());
        assertEquals("Otro", resultado.getNombre_genero());
        verify(generoRepository).save(any(Genero.class));
    }

    // ---------- actualizarGenero ----------

    @Test
    void actualizarGenero_deberiaActualizarYRetornarGenero_siExiste() {
        GeneroDTO.Request updateRequest = new GeneroDTO.Request();
        updateRequest.setNombre_genero("Masculino Actualizado");

        Genero generoActualizado = new Genero();
        generoActualizado.setId_genero(1);
        generoActualizado.setNombre_genero("Masculino Actualizado");

        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        when(generoRepository.save(any(Genero.class))).thenReturn(generoActualizado);

        GeneroDTO.Response resultado = generoService.actualizarGenero(1, updateRequest);

        assertEquals(1, resultado.getId_genero());
        assertEquals("Masculino Actualizado", resultado.getNombre_genero());
        verify(generoRepository).findById(1);
        verify(generoRepository).save(any(Genero.class));
    }

    @Test
    void actualizarGenero_deberiaLanzarExcepcion_siGeneroNoExiste() {
        when(generoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> generoService.actualizarGenero(99, generoRequest));

        assertThat(ex.getMessage()).contains("Género no encontrado con ID: 99");
        verify(generoRepository, never()).save(any());
    }

    @Test
    void actualizarGenero_deberiaGuardarCambios_siDatosValidos() {
        GeneroDTO.Request updateRequest = new GeneroDTO.Request();
        updateRequest.setNombre_genero("Femenino Actualizado");

        Genero generoActualizado = new Genero();
        generoActualizado.setId_genero(1);
        generoActualizado.setNombre_genero("Femenino Actualizado");

        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        when(generoRepository.save(any(Genero.class))).thenReturn(generoActualizado);

        GeneroDTO.Response resultado = generoService.actualizarGenero(1, updateRequest);

        assertEquals("Femenino Actualizado", resultado.getNombre_genero());
        verify(generoRepository).save(any(Genero.class));
    }

    // ---------- eliminarGenero ----------

    @Test
    void eliminarGenero_deberiaEliminarGenero_siExiste() {
        when(generoRepository.existsById(1)).thenReturn(true);
        doNothing().when(generoRepository).deleteById(1);

        generoService.eliminarGenero(1);

        verify(generoRepository).existsById(1);
        verify(generoRepository).deleteById(1);
    }

    @Test
    void eliminarGenero_deberiaLanzarExcepcion_siGeneroNoExiste() {
        when(generoRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> generoService.eliminarGenero(99));

        assertThat(ex.getMessage()).contains("Género no encontrado con ID: 99");
        verify(generoRepository, never()).deleteById(anyInt());
    }

    @Test
    void eliminarGenero_deberiaVerificarExistenciaAntesDeEliminar() {
        when(generoRepository.existsById(1)).thenReturn(true);
        doNothing().when(generoRepository).deleteById(1);

        generoService.eliminarGenero(1);

        verify(generoRepository).existsById(1);
        verify(generoRepository).deleteById(1);
    }
}
