package com.example.MicroPedido.service;

import java.util.List;

import com.example.MicroPedido.dto.ClienteDTO;

public interface ClienteService {

    List<ClienteDTO.Response> listarClientes();

    ClienteDTO.Response buscarPorId(int id_cliente);

    ClienteDTO.Response buscarPorNombre(String nombre);

    ClienteDTO.Response crearCliente(ClienteDTO.Request request);

    ClienteDTO.Response actualizarCliente(int id_cliente, ClienteDTO.Request request);

    void eliminarCliente(int id_cliente);
}