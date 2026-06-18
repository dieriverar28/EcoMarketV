package com.example.MicroUsuarioSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroUsuarioSeguridad.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    // findAll() y findById() ya vienen de JpaRepository, no hace falta redeclararlos
}