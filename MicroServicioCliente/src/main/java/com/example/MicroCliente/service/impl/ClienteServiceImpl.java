package com.example.MicroCliente.service.impl;

import com.example.MicroCliente.dto.ClienteDTO;
import com.example.MicroCliente.entity.Cliente;
import com.example.MicroCliente.entity.Genero;
import com.example.MicroCliente.repository.ClienteRepository;
import com.example.MicroCliente.repository.GeneroRepository;
import com.example.MicroCliente.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final GeneroRepository generoRepository;

    @Override
    public List<ClienteDTO.Response> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO.Response obtenerClientePorId(Integer id_cliente) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id_cliente));
        return mapToResponse(cliente);
    }

    @Override
    public List<ClienteDTO.Response> listarClientesPorGenero(Integer id_genero) {
        // Validar que el género existe antes de filtrar
        if (!generoRepository.existsById(id_genero)) {
            throw new RuntimeException("Género no encontrado con ID: " + id_genero);
        }
        return clienteRepository.findClientesByGeneroId(id_genero)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO.Response crearCliente(ClienteDTO.Request request) {
        Genero genero = generoRepository.findById(request.getId_genero())
                .orElseThrow(() -> new RuntimeException("Género no encontrado con ID: " + request.getId_genero()));

        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        cliente.setId_comuna(request.getId_comuna());
        cliente.setDireccion_envio(request.getDireccion_envio());
        cliente.setGenero(genero);

        return mapToResponse(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO.Response actualizarCliente(Integer id_cliente, ClienteDTO.Request request) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id_cliente));

        Genero genero = generoRepository.findById(request.getId_genero())
                .orElseThrow(() -> new RuntimeException("Género no encontrado con ID: " + request.getId_genero()));

        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        cliente.setId_comuna(request.getId_comuna());
        cliente.setDireccion_envio(request.getDireccion_envio());
        cliente.setGenero(genero);

        return mapToResponse(clienteRepository.save(cliente));
    }

    @Override
    public void eliminarCliente(Integer id_cliente) {
        if (!clienteRepository.existsById(id_cliente)) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id_cliente);
        }
        clienteRepository.deleteById(id_cliente);
    }

    // ── Mapper ──────────────────────────────────────────────────────────────
    private ClienteDTO.Response mapToResponse(Cliente cliente) {
        return new ClienteDTO.Response(
                cliente.getId_cliente(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getId_comuna(),
                cliente.getDireccion_envio(),
                cliente.getGenero().getNombre_genero() // devuelve nombre legible
        );
    }
}