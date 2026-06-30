package com.example.MicroTiendaUbicacion.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroTiendaUbicacion.dto.TiendaDTO;
import com.example.MicroTiendaUbicacion.entity.Tienda;
import com.example.MicroTiendaUbicacion.repository.TiendaRepository;

import antlr.collections.List;

// Esta anotación habilita el uso de Mockito en JUnit 5
@ExtendWith(MockitoExtension.class)
public class TiendaServiceImplTest {
   @Mock
    private TiendaRepository repository;

    @InjectMocks
    private TiendaServiceimpl tiendaService;

    @Test
    public void testListar() {
        // Arrange
        Tienda tienda1 = new Tienda(1, "Tienda A", "Direccion A", "123", "Comuna A", "Region A", "111", true, null);
        Tienda tienda2 = new Tienda(2, "Tienda B", "Direccion B", "456", "Comuna B", "Region B", "222", true, null);
        when(repository.findAll()).thenReturn(List.of(tienda1, tienda2));

        // Act
        List resultado = tiendaService.listar();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Tienda A", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testBuscarPorId_Exito() {
        // Arrange
        Tienda tienda = new Tienda(1, "Tienda A", "Direccion A", "123", "Comuna A", "Region A", "111", true, null);
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

        Tienda tiendaGuardada = new Tienda(1, "Nueva Tienda", "Calle 123", null, null, null, null, true, null);
        when(repository.save(any(Tienda.class))).thenReturn(tiendaGuardada);

        // Act
        TiendaDTO.Response resultado = tiendaService.guardar(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getId_tienda());
        assertEquals("Nueva Tienda", resultado.getNombre());
        verify(repository, times(1)).save(any(Tienda.class));
    }

    @Test
    public void testActualizar_Exito() {
        // Arrange
        Tienda tiendaExistente = new Tienda(1, "Tienda Vieja", "Calle vieja", null, null, null, null, true, null);
        
        TiendaDTO.Request request = new TiendaDTO.Request();
        request.setNombre("Tienda Actualizada");
        request.setDireccion("Calle Nueva");

        Tienda tiendaActualizada = new Tienda(1, "Tienda Actualizada", "Calle Nueva", null, null, null, null, true, null);

        when(repository.findById(1)).thenReturn(Optional.of(tiendaExistente));
        when(repository.save(any(Tienda.class))).thenReturn(tiendaActualizada);

        // Act
        TiendaDTO.Response resultado = tiendaService.actualizar(1, request);

        // Assert
        assertNotNull(resultado);
        assertEquals("Tienda Actualizada", resultado.getNombre());
    }

    @Test
    public void testEliminar() {
        // Act
        tiendaService.eliminar(1);

        // Assert
        verify(repository, times(1)).deleteById(1); 
    }
}
