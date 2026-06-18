package com.example.MicroInventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroInventario.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // Buscar categoría por nombre
    Optional<Categoria> findByNombre(String nombre);

}