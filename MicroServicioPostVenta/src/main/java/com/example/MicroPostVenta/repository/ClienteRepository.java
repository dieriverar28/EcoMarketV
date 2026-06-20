package com.example.MicroPostVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.MicroPostVenta.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

   @Query("SELECT c FROM Cliente c WHERE c.genero = :id_genero")
    List<Cliente> findClientesByGeneroId(@Param("id_genero") Integer id_genero);
}
