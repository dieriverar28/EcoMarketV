package com.example.MicroCliente.service;

import com.example.MicroCliente.dto.ClienteDTO;
import java.util.List;

public interface ClienteService {

    List<ClienteDTO.Response> listarClientes();

    ClienteDTO.Response obtenerClientePorId(Integer id_cliente);

    List<ClienteDTO.Response> listarClientesPorGenero(Integer id_genero);

    ClienteDTO.Response crearCliente(ClienteDTO.Request request);

    ClienteDTO.Response actualizarCliente(Integer id_cliente, ClienteDTO.Request request);

    void eliminarCliente(Integer id_cliente);
}