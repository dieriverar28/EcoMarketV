package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.ClienteDTO;
import com.example.MicroEnvio.entity.Cliente;
import com.example.MicroEnvio.repository.ClienteRepository;
import com.example.MicroEnvio.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO.Response> listarTodos() {
        return clienteRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO.Response buscarPorId(int id) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        return mapToResponse(c);
    }

    @Override
    @Transactional
    public ClienteDTO.Response crear(ClienteDTO.Request request) {
        if (clienteRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Ya existe un cliente con el email: " + request.getEmail());
        Cliente c = new Cliente();
        c.setNombre(request.getNombre());
        c.setEmail(request.getEmail());
        c.setTelefono(request.getTelefono());
        c.setDireccion_envio(request.getDireccion_envio());
        c.setComuna(request.getId_comuna());
        return mapToResponse(clienteRepository.save(c));
    }

    @Override
    @Transactional
    public ClienteDTO.Response actualizar(int id, ClienteDTO.Request request) {
        Cliente c = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        c.setNombre(request.getNombre());
        c.setEmail(request.getEmail());
        c.setTelefono(request.getTelefono());
        c.setDireccion_envio(request.getDireccion_envio());
        c.setComuna(request.getId_comuna());
        return mapToResponse(clienteRepository.save(c));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!clienteRepository.existsById(id))
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        clienteRepository.deleteById(id);
    }

    private ClienteDTO.Response mapToResponse(Cliente c) {
        return new ClienteDTO.Response(
                c.getId(), c.getNombre(), c.getEmail(),
                c.getTelefono(), c.getDireccion_envio(), c.getComuna());
    }
}

