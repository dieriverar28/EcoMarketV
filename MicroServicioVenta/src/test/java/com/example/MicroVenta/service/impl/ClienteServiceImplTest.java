package com.example.MicroVenta.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroVenta.dto.ClienteDTO;
import com.example.MicroVenta.entity.Cliente;
import com.example.MicroVenta.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO.Request request;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Juan Pérez");
        cliente.setEmail("juan@example.com");
        cliente.setTelefono("987654321");
        cliente.setComuna(1);
        cliente.setDireccion_envio("Av. Siempre Viva 123");
        cliente.setGenero(1);

        request = new ClienteDTO.Request();
        request.setId_cliente(2);
        request.setNombre("Ana García");
        request.setEmail("ana@example.com");
        request.setGeneroId(2);
        request.setId_comuna(3);
        request.setTelefono("912345678");
        request.setDireccion_envio("Calle Falsa 456");
    }

    @Test
    void listarClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO.Response> resultado = clienteService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNombre()).isEqualTo("Juan Pérez");
        assertThat(resultado.get(0).getEmail()).isEqualTo("juan@example.com");
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void buscarClientePorId() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteDTO.Response resultado = clienteService.buscarPorId(1);

        assertThat(resultado.getId_cliente()).isEqualTo(1);
        assertThat(resultado.getNombre()).isEqualTo("Juan Pérez");
        assertThat(resultado.getEmail()).isEqualTo("juan@example.com");
    }

    @Test
    void buscarClientePorIdNoExiste() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.buscarPorId(99));

        assertThat(ex.getMessage()).contains("Cliente no encontrado con id: 99");
    }

    @Test
    void crearCliente() {
        when(clienteRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO.Response resultado = clienteService.crear(request);

        assertThat(resultado.getNombre()).isEqualTo("Juan Pérez");
        assertThat(resultado.getEmail()).isEqualTo("juan@example.com");
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void crearClienteEmailDuplicado() {
        when(clienteRepository.existsByEmail(request.getEmail())).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.crear(request));

        assertThat(ex.getMessage()).contains("Ya existe un cliente con el email");
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void actualizarCliente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO.Response resultado = clienteService.actualizar(1, request);

        assertThat(cliente.getNombre()).isEqualTo("Ana García");
        assertThat(cliente.getEmail()).isEqualTo("ana@example.com");
        assertThat(cliente.getTelefono()).isEqualTo("912345678");
        assertThat(resultado.getNombre()).isEqualTo("Ana García");
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void actualizarClienteNoExiste() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.actualizar(99, request));

        assertThat(ex.getMessage()).contains("Cliente no encontrado con id: 99");
    }

    @Test
    void eliminarCliente() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.eliminar(1);

        verify(clienteRepository).existsById(1);
        verify(clienteRepository).deleteById(1);
    }

    @Test
    void eliminarClienteNoExiste() {
        when(clienteRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clienteService.eliminar(99));

        assertThat(ex.getMessage()).contains("Cliente no encontrado con id: 99");
    }
}
