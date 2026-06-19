package com.example.MicroPedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroPedido.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByEstado(boolean estado);

    boolean existsByNombreIgnoreCase(String nombre);
}
