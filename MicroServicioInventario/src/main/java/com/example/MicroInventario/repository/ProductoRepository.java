package com.example.MicroInventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroInventario.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoria_Nombre(String nombre);

    List<Producto> findByEstado(boolean estado);

    boolean existsByNombreIgnoreCase(String nombre);
}