package com.example.MicroPedido.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPedido.dto.ClienteDTO;
import com.example.MicroPedido.model.Cliente;
import com.example.MicroPedido.repository.ClienteRepository;
import com.example.MicroPedido.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // LISTAR TODOS
    @Override
    public List<ClienteDTO.Response> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // BUSCAR POR ID
    @Override
    public ClienteDTO.Response buscarPorId(int id_cliente) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id_cliente));
        return toResponse(cliente);
    }

    // BUSCAR POR NOMBRE
    @Override
    public ClienteDTO.Response buscarPorNombre(String nombre) {
    Cliente cliente = clienteRepository.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con nombre: " + nombre));
    return toResponse(cliente);
}

    // CREAR
    @Override
    public ClienteDTO.Response crearCliente(ClienteDTO.Request request) {
        Cliente cliente = toEntity(request);
        return toResponse(clienteRepository.save(cliente));
    }

    // ACTUALIZAR
    @Override
    public ClienteDTO.Response actualizarCliente(int id_cliente, ClienteDTO.Request request) {
        Cliente cliente = clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id_cliente));
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion_envio(request.getDireccion_envio());
        cliente.setId_comuna(request.getId_comuna());
        return toResponse(clienteRepository.save(cliente));
    }

    // ELIMINAR
    @Override
    public void eliminarCliente(int id_cliente) {
        if (!clienteRepository.existsById(id_cliente))
            throw new RuntimeException("Cliente no encontrado con id: " + id_cliente);
        clienteRepository.deleteById(id_cliente);
    }

    // MAPPER entidad → DTO
    private ClienteDTO.Response toResponse(Cliente c) {
        ClienteDTO.Response dto = new ClienteDTO.Response();
        dto.setId_cliente(c.getId_cliente());
        dto.setNombre(c.getNombre());
        dto.setTelefono(c.getTelefono());
        dto.setDireccion_envio(c.getDireccion_envio());
        dto.setId_comuna(c.getId_comuna());
        return dto;
    }

    // MAPPER DTO → entidad
    private Cliente toEntity(ClienteDTO.Request request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion_envio(request.getDireccion_envio());
        cliente.setId_comuna(request.getId_comuna());
        return cliente;
    }
}