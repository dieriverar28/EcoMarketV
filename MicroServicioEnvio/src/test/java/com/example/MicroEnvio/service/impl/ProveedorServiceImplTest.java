package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.ProveedorDTO;
import com.example.MicroEnvio.entity.Proveedor;
import com.example.MicroEnvio.repository.ProveedorRepository;
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
class ProveedorServiceImplTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private Proveedor proveedor;
    private ProveedorDTO.Request proveedorRequest;

    @BeforeEach
    void setUp() {
        proveedor = new Proveedor(1, "Transportes Sur");
        proveedorRequest = new ProveedorDTO.Request("Transportes Sur");
    }

    @Test
    void listarTodo_RetornarListaDeProveedores() {
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));

        List<ProveedorDTO.Response> resultado = proveedorService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Transportes Sur", resultado.get(0).getNombre());
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    void Id_ExisteRetornarProveedor() {
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        ProveedorDTO.Response resultado = proveedorService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("Transportes Sur", resultado.getNombre());
        verify(proveedorRepository, times(1)).findById(1);
    }

    @Test
    void Id_NoExisteLanzarExcepcion() {
        when(proveedorRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> proveedorService.buscarPorId(99));
        assertTrue(ex.getMessage().contains("Proveedor no encontrado"));
    }

    @Test
    void crear_NombreNoExisteCrearProveedor() {
        when(proveedorRepository.existsByNombre(proveedorRequest.getNombre())).thenReturn(false);
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedor);

        ProveedorDTO.Response resultado = proveedorService.crear(proveedorRequest);

        assertNotNull(resultado);
        assertEquals("Transportes Sur", resultado.getNombre());
        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }

    @Test
    void crear_NombreYaExisteLanzarExcepcion() {
        when(proveedorRepository.existsByNombre(proveedorRequest.getNombre())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> proveedorService.crear(proveedorRequest));
        assertTrue(ex.getMessage().contains("Ya existe un proveedor"));
        verify(proveedorRepository, never()).save(any(Proveedor.class));
    }

    @Test
    void actualizar_ExisteActualizarProveedor() {
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedor);

        ProveedorDTO.Response resultado = proveedorService.actualizar(1, proveedorRequest);

        assertNotNull(resultado);
        assertEquals("Transportes Sur", resultado.getNombre());
        verify(proveedorRepository, times(1)).save(any(Proveedor.class));
    }

    @Test
    void actualizar_NoExisteLanzarExcepcion() {
        when(proveedorRepository.findById(anyInt())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> proveedorService.actualizar(99, proveedorRequest));
        assertTrue(ex.getMessage().contains("Proveedor no encontrado"));
        verify(proveedorRepository, never()).save(any(Proveedor.class));
    }

    @Test
    void eliminar_ExisteEliminarProveedor() {
        when(proveedorRepository.existsById(1)).thenReturn(true);

        proveedorService.eliminar(1);

        verify(proveedorRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_NoExisteLanzarExcepcion() {
        when(proveedorRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> proveedorService.eliminar(99));
        assertTrue(ex.getMessage().contains("Proveedor no encontrado"));
        verify(proveedorRepository, never()).deleteById(anyInt());
    }
}
