package com.example.MicroUsuarioSeguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroUsuarioSeguridad.model.RolPermiso;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {

}