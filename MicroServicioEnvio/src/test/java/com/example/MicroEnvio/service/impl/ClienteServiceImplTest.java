package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.ClienteDTO;
import com.example.MicroEnvio.entity.Cliente;
import com.example.MicroEnvio.repository.ClienteRepository;
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
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO.Request clienteRequest;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Juan Perez", "juan@correo.com", "987654321", "Av. Siempre Viva 123", 5);
        clienteRequest = new ClienteDTO.Request("Juan Perez", "juan@correo.com", "987654321", "Av. Siempre Viva 123", 5);
    }

    @Test
    void RetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO.Response> resultado = clienteService.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombre());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void IddebeRetornarCliente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteDTO.Response resultado = clienteService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("juan@correo.com", resultado.getEmail());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void IdcuandoNoExistedebeLanzarExcepcion() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.buscarPorId(99));
        assertTrue(ex.getMessage().contains("Cliente no encontrado"));
    }

    @Test
    void EmailNoExiste_CrearCliente() {
        when(clienteRepository.existsByEmail(clienteRequest.getEmail())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO.Response resultado = clienteService.crear(clienteRequest);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void crear_EmailYaExisteLanzarExcepcion() {
        when(clienteRepository.existsByEmail(clienteRequest.getEmail())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.crear(clienteRequest));
        assertTrue(ex.getMessage().contains("Ya existe un cliente"));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void cuandoExisteActualizarCliente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO.Response resultado = clienteService.actualizar(1, clienteRequest);

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombre());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void act_cuandoNoExisteLanzarExcepcion() {
        when(clienteRepository.findById(anyInt())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.actualizar(99, clienteRequest));
        assertTrue(ex.getMessage().contains("Cliente no encontrado"));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void eliminar_cuandoExiste() {
        when(clienteRepository.existsById(1)).thenReturn(true);

        clienteService.eliminar(1);

        verify(clienteRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminar_cuandoNoExisteLanzarExcepcion() {
        when(clienteRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.eliminar(99));
        assertTrue(ex.getMessage().contains("Cliente no encontrado"));
        verify(clienteRepository, never()).deleteById(anyInt());
    }
}
