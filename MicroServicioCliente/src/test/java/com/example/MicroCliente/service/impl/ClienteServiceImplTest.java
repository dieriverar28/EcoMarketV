package com.example.MicroCliente.service.impl;

import com.example.MicroCliente.dto.ClienteDTO;
import com.example.MicroCliente.entity.Cliente;
import com.example.MicroCliente.entity.Genero;
import com.example.MicroCliente.repository.ClienteRepository;
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
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private Genero genero;
    private ClienteDTO.Request clienteRequest;
    private ClienteDTO.Response clienteResponse;

    @BeforeEach
    void setUp() {
        genero = new Genero();
        genero.setId_genero(1);
        genero.setNombre_genero("Masculino");

        cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Juan Pérez");
        cliente.setTelefono("987654321");
        cliente.setComuna("Santiago");
        cliente.setDireccion_envio("Calle Principal 123");
        cliente.setEmail("juan@email.com");
        cliente.setGenero(genero);

        clienteRequest = new ClienteDTO.Request();
        clienteRequest.setNombre("Juan Pérez");
        clienteRequest.setTelefono("987654321");
        clienteRequest.setComuna("Santiago");
        clienteRequest.setDireccion_envio("Calle Principal 123");
        clienteRequest.setEmail("juan@email.com");
        clienteRequest.setId_genero(1);

        clienteResponse = new ClienteDTO.Response(
                1,
                "Juan Pérez",
                "987654321",
                "Santiago",
                "Calle Principal 123",
                "juan@email.com",
                "Masculino"
        );
    }

    // ---------- listarClientes ----------

    @Test
    void listarClientes_deberiaRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO.Response> resultado = clienteService.listarClientes();

        assertThat(resultado).hasSize(1);
        assertEquals("Juan Pérez", resultado.get(0).getNombre());
        assertEquals("juan@email.com", resultado.get(0).getEmail());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void listarClientes_deberiaRetornarListaVacia_siNoHayClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of());

        List<ClienteDTO.Response> resultado = clienteService.listarClientes();

        assertThat(resultado).isEmpty();
    }

    // ---------- obtenerClientePorId ----------

    @Test
    void obtenerClientePorId_deberiaRetornarCliente_siExiste() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ClienteDTO.Response resultado = clienteService.obtenerClientePorId(1);

        assertEquals(1, resultado.getId_cliente());
        assertEquals("Juan Pérez", resultado.getNombre());
        assertEquals("juan@email.com", resultado.getEmail());
    }

    @Test
    void obtenerClientePorId_deberiaLanzarExcepcion_siNoExiste() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.obtenerClientePorId(99));

        assertThat(ex.getMessage()).contains("Cliente no encontrado con ID: 99");
    }

    // ---------- listarClientesPorGenero ----------

    @Test
    void listarClientesPorGenero_deberiaRetornarClientesFiltrados_siGeneroExiste() {
        when(generoRepository.existsById(1)).thenReturn(true);
        when(clienteRepository.findClientesByGeneroId(1)).thenReturn(List.of(cliente));

        List<ClienteDTO.Response> resultado = clienteService.listarClientesPorGenero(1);

        assertThat(resultado).hasSize(1);
        assertEquals("Masculino", resultado.get(0).getNombre_genero());
        verify(generoRepository).existsById(1);
        verify(clienteRepository).findClientesByGeneroId(1);
    }

    @Test
    void listarClientesPorGenero_deberiaRetornarListaVacia_siNoHayClientesDelGenero() {
        when(generoRepository.existsById(1)).thenReturn(true);
        when(clienteRepository.findClientesByGeneroId(1)).thenReturn(List.of());

        List<ClienteDTO.Response> resultado = clienteService.listarClientesPorGenero(1);

        assertThat(resultado).isEmpty();
    }

    @Test
    void listarClientesPorGenero_deberiaLanzarExcepcion_siGeneroNoExiste() {
        when(generoRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.listarClientesPorGenero(99));

        assertThat(ex.getMessage()).contains("Género no encontrado con ID: 99");
        verify(clienteRepository, never()).findClientesByGeneroId(anyInt());
    }

    // ---------- crearCliente ----------

    @Test
    void crearCliente_deberiaGuardarYRetornarCliente_siDatosValidos() {
        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDTO.Response resultado = clienteService.crearCliente(clienteRequest);

        assertEquals("Juan Pérez", resultado.getNombre());
        assertEquals("juan@email.com", resultado.getEmail());
        assertEquals("Masculino", resultado.getNombre_genero());
        verify(generoRepository).findById(1);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void crearCliente_deberiaLanzarExcepcion_siGeneroNoExiste() {
        when(generoRepository.findById(99)).thenReturn(Optional.empty());

        clienteRequest.setId_genero(99);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.crearCliente(clienteRequest));

        assertThat(ex.getMessage()).contains("Género no encontrado con ID: 99");
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void crearCliente_deberiaMapearCorrectamenteLaRespuesta() {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setId_cliente(2);
        nuevoCliente.setNombre("María García");
        nuevoCliente.setTelefono("912345678");
        nuevoCliente.setComuna("Providencia");
        nuevoCliente.setDireccion_envio("Avenida Secundaria 456");
        nuevoCliente.setEmail("maria@email.com");
        nuevoCliente.setGenero(genero);

        ClienteDTO.Request request = new ClienteDTO.Request();
        request.setNombre("María García");
        request.setTelefono("912345678");
        request.setComuna("Providencia");
        request.setDireccion_envio("Avenida Secundaria 456");
        request.setEmail("maria@email.com");
        request.setId_genero(1);

        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(nuevoCliente);

        ClienteDTO.Response resultado = clienteService.crearCliente(request);

        assertEquals(2, resultado.getId_cliente());
        assertEquals("María García", resultado.getNombre());
        assertEquals("maria@email.com", resultado.getEmail());
    }

    // ---------- actualizarCliente ----------

    @Test
    void actualizarCliente_deberiaActualizarYRetornarCliente_siExiste() {
        ClienteDTO.Request updateRequest = new ClienteDTO.Request();
        updateRequest.setNombre("Juan Pérez Actualizado");
        updateRequest.setTelefono("999999999");
        updateRequest.setComuna("Ñuñoa");
        updateRequest.setDireccion_envio("Calle Nueva 789");
        updateRequest.setEmail("juannuevo@email.com");
        updateRequest.setId_genero(1);

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(generoRepository.findById(1)).thenReturn(Optional.of(genero));
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ClienteDTO.Response resultado = clienteService.actualizarCliente(1, updateRequest);

        assertEquals("Juan Pérez Actualizado", resultado.getNombre());
        assertEquals("999999999", resultado.getTelefono());
        assertEquals("juannuevo@email.com", resultado.getEmail());
        verify(clienteRepository).findById(1);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void actualizarCliente_deberiaLanzarExcepcion_siClienteNoExiste() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.actualizarCliente(99, clienteRequest));

        assertThat(ex.getMessage()).contains("Cliente no encontrado con ID: 99");
        verify(clienteRepository, never()).save(any());
    }

    @Test
    void actualizarCliente_deberiaLanzarExcepcion_siGeneroNoExiste() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(generoRepository.findById(99)).thenReturn(Optional.empty());

        clienteRequest.setId_genero(99);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.actualizarCliente(1, clienteRequest));

        assertThat(ex.getMessage()).contains("Género no encontrado con ID: 99");
        verify(clienteRepository, never()).save(any());
    }

    // ---------- eliminarCliente ----------

    @Test
    void eliminarCliente_deberiaEliminarCliente_siExiste() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.eliminarCliente(1);

        verify(clienteRepository).existsById(1);
        verify(clienteRepository).deleteById(1);
    }

    @Test
    void eliminarCliente_deberiaLanzarExcepcion_siClienteNoExiste() {
        when(clienteRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> clienteService.eliminarCliente(99));

        assertThat(ex.getMessage()).contains("Cliente no encontrado con ID: 99");
        verify(clienteRepository, never()).deleteById(anyInt());
    }

    @Test
    void eliminarCliente_deberiaVerificarExistenciaAntesDeEliminar() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1);

        clienteService.eliminarCliente(1);

        verify(clienteRepository).existsById(1);
        verify(clienteRepository).deleteById(1);
    }
}
