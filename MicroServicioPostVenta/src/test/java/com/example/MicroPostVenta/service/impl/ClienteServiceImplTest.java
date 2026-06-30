package com.example.MicroPostVenta.service.impl;

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

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.repository.ClienteRepository;

public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceimpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarClientes() {
        Cliente cliente = crearCliente();
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.obtenerClientes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(cliente.getNombre(), resultado.get(0).getNombre());
    }

    @Test
    void buscarClientePorId() {
        Cliente cliente = crearCliente();
        when(clienteRepository.findById(cliente.getId_cliente())).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.buscarCliente(cliente.getId_cliente());

        assertNotNull(resultado);
        assertEquals(cliente.getId_cliente(), resultado.getId_cliente());
        assertEquals(cliente.getEmail(), resultado.getEmail());
    }

    @Test
    void guardarCliente() {
        Cliente cliente = crearCliente();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.crearCliente(cliente);

        assertNotNull(resultado);
        assertEquals(cliente.getNombre(), resultado.getNombre());
        assertEquals(cliente.getEmail(), resultado.getEmail());
    }

    @Test
    void actualizarCliente() {
        Cliente cliente = crearCliente();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.actualizarCliente(cliente);

        assertNotNull(resultado);
        assertEquals(cliente.getNombre(), resultado.getNombre());
    }

    @Test
    void eliminarCliente() {
        int id = 1;
        when(clienteRepository.existsById(id)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(id);

        int resultado = clienteService.eliminarCliente(id);

        assertEquals(1, resultado);
        verify(clienteRepository).deleteById(id);
    }

    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Cliente prueba");
        cliente.setEmail("cliente@prueba.com");
        cliente.setTelefono("1234567890");
        cliente.setComuna(1);
        cliente.setDireccion_envio("Direccion prueba");
        cliente.setGenero(1);
        return cliente;
    }
}

