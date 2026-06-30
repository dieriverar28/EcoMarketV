package com.example.MicroTiendaUbicacion.service.impl;

import com.example.MicroTiendaUbicacion.dto.HorarioTiendaDTO;
import com.example.MicroTiendaUbicacion.entity.HorarioTienda;
import com.example.MicroTiendaUbicacion.entity.Tienda;
import com.example.MicroTiendaUbicacion.repository.HorarioTiendaRepository;
import com.example.MicroTiendaUbicacion.repository.TiendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HorarioTiendaServiceImplTest {

    @Mock
    private HorarioTiendaRepository horarioRepository;

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private HorarioTiendaServiceimpl horarioService; // Verifica que coincida la 'i' minúscula con tu clase real

    @Test
    public void testListar() {
        // Arrange
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);

        HorarioTienda h1 = new HorarioTienda();
        h1.setId_horario(1);
        h1.setTienda(tienda);
        h1.setDia_semana(HorarioTienda.DiaSemana.LUNES);
        h1.setHora_apertura(LocalTime.of(9, 0));
        h1.setHora_cierre(LocalTime.of(18, 0));
        h1.setCerrado(false);

        when(horarioRepository.findAll()).thenReturn(Arrays.asList(h1));

        // Act
        List<HorarioTiendaDTO.Response> resultado = horarioService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("LUNES", resultado.get(0).getDia_semana());
        verify(horarioRepository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId_Exito() {
        // Arrange
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);

        HorarioTienda horario = new HorarioTienda();
        horario.setId_horario(1);
        horario.setTienda(tienda);
        horario.setDia_semana(HorarioTienda.DiaSemana.MARTES);
        horario.setHora_apertura(LocalTime.of(9, 0));
        horario.setHora_cierre(LocalTime.of(18, 0));
        horario.setCerrado(false);

        when(horarioRepository.findById(1)).thenReturn(Optional.of(horario));

        // Act
        HorarioTiendaDTO.Response resultado = horarioService.buscarPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("MARTES", resultado.getDia_semana());
    }

    @Test
    public void testGuardar_Exito() {
        // Arrange
        HorarioTiendaDTO.Request request = new HorarioTiendaDTO.Request();
        request.setId_tienda(1);
        request.setDia_semana("MIERCOLES");
        request.setHora_apertura(LocalTime.of(10, 0));
        request.setHora_cierre(LocalTime.of(20, 0));
        request.setCerrado(false);

        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);

        HorarioTienda horarioGuardado = new HorarioTienda();
        horarioGuardado.setId_horario(5);
        horarioGuardado.setTienda(tienda);
        horarioGuardado.setDia_semana(HorarioTienda.DiaSemana.MIERCOLES);
        horarioGuardado.setHora_apertura(LocalTime.of(10, 0));
        horarioGuardado.setHora_cierre(LocalTime.of(20, 0));
        horarioGuardado.setCerrado(false);

        when(tiendaRepository.findById(1)).thenReturn(Optional.of(tienda));
        when(horarioRepository.save(any(HorarioTienda.class))).thenReturn(horarioGuardado);

        // Act
        HorarioTiendaDTO.Response resultado = horarioService.guardar(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(5, resultado.getId_horario());
        assertEquals("MIERCOLES", resultado.getDia_semana());
    }

    @Test
    public void testEliminar() {
        // Act
        horarioService.eliminar(3);

        // Assert
        verify(horarioRepository, times(1)).deleteById(3);
    }

    @Test
    public void testBuscarPorTienda() {
        // Arrange
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);

        HorarioTienda h1 = new HorarioTienda();
        h1.setId_horario(1);
        h1.setTienda(tienda);
        h1.setDia_semana(HorarioTienda.DiaSemana.VIERNES);
        h1.setHora_apertura(LocalTime.of(9, 0));
        h1.setHora_cierre(LocalTime.of(18, 0));
        h1.setCerrado(false);

        when(horarioRepository.findByTienda(1)).thenReturn(Arrays.asList(h1));

        // Act
        List<HorarioTiendaDTO.Response> resultado = horarioService.buscarPorTienda(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getId_tienda());
    }
}