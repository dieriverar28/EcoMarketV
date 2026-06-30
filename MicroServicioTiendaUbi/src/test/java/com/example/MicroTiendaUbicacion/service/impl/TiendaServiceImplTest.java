package com.example.MicroTiendaUbicacion.service.impl;

import com.example.MicroTiendaUbicacion.dto.TiendaDTO;
import com.example.MicroTiendaUbicacion.entity.Tienda;
import com.example.MicroTiendaUbicacion.repository.TiendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays; // Cambiado para máxima compatibilidad
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TiendaServiceImplTest {

    @Mock
    private TiendaRepository repository;

    @InjectMocks
    private TiendaServiceimpl tiendaService; // Ojo: verifica que coincida con la 'i' minúscula de tu clase original

    @Test
    public void testListar() {
        // Arrange: Creamos los objetos usando setters para evitar errores de versión
        Tienda tienda1 = new Tienda();
        tienda1.setId_tienda(1);
        tienda1.setNombre("Tienda A");
        tienda1.setDireccion("Direccion A");
        tienda1.setActiva(true);

        Tienda tienda2 = new Tienda();
        tienda2.setId_tienda(2);
        tienda2.setNombre("Tienda B");
        tienda2.setDireccion("Direccion B");
        tienda2.setActiva(true);

        // Arrays.asList nunca falla en ninguna versión de Java
        when(repository.findAll()).thenReturn(Arrays.asList(tienda1, tienda2));

        // Act: Especificamos <TiendaDTO.Response> obligatoriamente
        List<TiendaDTO.Response> resultado = tiendaService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Tienda A", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId_Exito() {
        // Arrange
        Tienda tienda = new Tienda();
        tienda.setId_tienda(1);
        tienda.setNombre("Tienda A");
        when(repository.findById(1)).thenReturn(Optional.of(tienda));

        // Act
        TiendaDTO.Response resultado = tiendaService.buscarPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals("Tienda A", resultado.getNombre());
    }

    @Test
    public void testBuscarPorId_NoEncontrado() {
        // Arrange
        when(repository.findById(99)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tiendaService.buscarPorId(99);
        });
        assertEquals("Tienda no encontrada", exception.getMessage());
    }

    @Test
    public void testGuardar() {
        // Arrange
        TiendaDTO.Request request = new TiendaDTO.Request();
        request.setNombre("Nueva Tienda");
        request.setDireccion("Calle 123");
        request.setActiva(true);

        Tienda tiendaGuardada = new Tienda();
        tiendaGuardada.setId_tienda(1);
        tiendaGuardada.setNombre("Nueva Tienda");
        tiendaGuardada.setDireccion("Calle 123");
        tiendaGuardada.setActiva(true);

        when(repository.save(any(Tienda.class))).thenReturn(tiendaGuardada);

        // Act
        TiendaDTO.Response resultado = tiendaService.guardar(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId_tienda());
        assertEquals("Nueva Tienda", resultado.getNombre());
    }

    @Test
    public void testEliminar() {
        // Act
        tiendaService.eliminar(1);

        // Assert
        verify(repository, times(1)).deleteById(1);
    }
}