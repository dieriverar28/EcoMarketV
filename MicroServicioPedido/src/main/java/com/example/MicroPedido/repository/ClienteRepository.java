package com.example.MicroPedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroPedido.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    //  Buscar por nombre (único método necesario)
    Optional<Cliente> findByNombre(String nombre);

   
}
