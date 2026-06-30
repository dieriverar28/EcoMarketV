package com.example.MicroUsuarioSeguridad.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroUsuarioSeguridad.dto.GeneroDTO;
import com.example.MicroUsuarioSeguridad.entity.Genero;
import com.example.MicroUsuarioSeguridad.repository.GeneroRepository;

public class GeneroServiceImplTest {

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private GeneroServiceImpl generoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarGeneros() {
        Genero genero = crearGenero();
        when(generoRepository.findAll()).thenReturn(List.of(genero));

        List<GeneroDTO.Response> resultado = generoService.listarGeneros();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(genero.getNombre_genero(), resultado.get(0).getNombre_genero());
    }

    @Test
    void obtenerGeneroPorId() {
        Genero genero = crearGenero();
        when(generoRepository.findById(genero.getId_genero())).thenReturn(Optional.of(genero));

        GeneroDTO.Response resultado = generoService.obtenerGeneroPorId(genero.getId_genero());

        assertNotNull(resultado);
        assertEquals(genero.getId_genero(), resultado.getId_genero());
        assertEquals(genero.getNombre_genero(), resultado.getNombre_genero());
    }

    @Test
    void crearGeneroRequest() {
        GeneroDTO.Request request = crearRequest();
        Genero generoGuardado = crearGenero();
        when(generoRepository.save(any(Genero.class))).thenReturn(generoGuardado);

        GeneroDTO.Response resultado = generoService.crearGenero(request);

        assertNotNull(resultado);
        assertEquals(generoGuardado.getNombre_genero(), resultado.getNombre_genero());
    }

    @Test
    void actualizarGenero() {
        GeneroDTO.Request request = crearRequest();
        Genero generoExistente = crearGenero();
        Genero generoActualizado = crearGenero();
        generoActualizado.setNombre_genero("Femenino");

        when(generoRepository.findById(generoExistente.getId_genero())).thenReturn(Optional.of(generoExistente));
        when(generoRepository.save(any(Genero.class))).thenReturn(generoActualizado);

        GeneroDTO.Response resultado = generoService.actualizarGenero(generoExistente.getId_genero(), request);

        assertNotNull(resultado);
        assertEquals(generoActualizado.getNombre_genero(), resultado.getNombre_genero());
    }

    @Test
    void eliminarGenero() {
        int id = 1;
        when(generoRepository.existsById(id)).thenReturn(true);
        doNothing().when(generoRepository).deleteById(id);

        generoService.eliminarGenero(id);

        verify(generoRepository).deleteById(id);
    }

    private GeneroDTO.Request crearRequest() {
        return new GeneroDTO.Request("Masculino");
    }

    private Genero crearGenero() {
        Genero genero = new Genero();
        genero.setId_genero(1);
        genero.setNombre_genero("Masculino");
        return genero;
    }
}

