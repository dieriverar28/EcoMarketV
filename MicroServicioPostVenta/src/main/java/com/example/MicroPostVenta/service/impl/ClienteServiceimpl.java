package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.repository.ClienteRepository;
import com.example.MicroPostVenta.service.ClienteService;

@Service
@Transactional
public class ClienteServiceimpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarCliente(int id_cliente) {
        return clienteRepository.findById(id_cliente).orElse(new Cliente());
    }

    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public int eliminarCliente(int id_cliente) {
        if (clienteRepository.existsById(id_cliente)) {
            clienteRepository.deleteById(id_cliente);
            return 1;
        }
        return 0;
    }
}

