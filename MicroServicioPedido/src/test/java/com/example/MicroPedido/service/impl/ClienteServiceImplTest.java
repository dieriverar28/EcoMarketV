package com.example.MicroPedido.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.dto.ClienteDTO;
import com.example.MicroPedido.entity.Cliente;
import com.example.MicroPedido.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void listarClientes() {
        Cliente cliente = new Cliente(1, "Ana", "912345678", 101, "Calle 1");
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO.Response> result = clienteService.listarClientes();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Ana");
    }

    @Test
    void buscarClientePorId() {
        Cliente cliente = new Cliente(2, "Luis", "987654321", 202, "Av. Siempre Viva");
        when(clienteRepository.findById(2)).thenReturn(Optional.of(cliente));

        ClienteDTO.Response result = clienteService.buscarPorId(2);

        assertThat(result.getId_cliente()).isEqualTo(2);
        assertThat(result.getTelefono()).isEqualTo("987654321");
    }

    @Test
    void buscarClientePorNombre() {
        Cliente cliente = new Cliente(3, "Mario", "999999999", 303, "Los Pinos");
        when(clienteRepository.findByNombre("Mario")).thenReturn(Optional.of(cliente));

        ClienteDTO.Response result = clienteService.buscarPorNombre("Mario");

        assertThat(result.getNombre()).isEqualTo("Mario");
    }

    @Test
    void crearCliente() {
        ClienteDTO.Request request = new ClienteDTO.Request(0, "Pedro", "911111111", 404, "San Martín 55");
        Cliente cliente = new Cliente(4, "Pedro", "911111111", 404, "San Martín 55");
        when(clienteRepository.save(org.mockito.ArgumentMatchers.any(Cliente.class))).thenReturn(cliente);

        ClienteDTO.Response result = clienteService.crearCliente(request);

        assertThat(result.getId_cliente()).isEqualTo(4);
        assertThat(result.getNombre()).isEqualTo("Pedro");
    }

    @Test
    void actualizarCliente() {
        Cliente cliente = new Cliente(5, "Viejo", "111111111", 505, "Antigua");
        ClienteDTO.Request request = new ClienteDTO.Request(0, "Nuevo", "222222222", 606, "Nueva");
        when(clienteRepository.findById(5)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteDTO.Response result = clienteService.actualizarCliente(5, request);

        assertThat(result.getNombre()).isEqualTo("Nuevo");
        assertThat(result.getTelefono()).isEqualTo("222222222");
        verify(clienteRepository).save(cliente);
    }

    @Test
    void eliminarCliente() {
        when(clienteRepository.existsById(6)).thenReturn(true);

        clienteService.eliminarCliente(6);

        verify(clienteRepository).deleteById(6);
    }

    @Test
    void buscarClienteInexistente() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.buscarPorId(99))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Cliente no encontrado");
    }
}
