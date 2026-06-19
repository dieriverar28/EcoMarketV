package com.example.MicroUsuarioSeguridad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroUsuarioSeguridad.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);

    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    List<Usuario> findByEstadoTrue();

    List<Usuario> findByEstadoFalse();

    List<Usuario> findByIdTienda(Integer idTienda);

}
