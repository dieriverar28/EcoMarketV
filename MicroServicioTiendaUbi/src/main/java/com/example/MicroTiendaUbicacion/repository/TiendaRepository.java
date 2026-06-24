package com.example.MicroTiendaUbicacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MicroTiendaUbicacion.entity.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {

    List<Tienda> findByActivaTrue();

    List<Tienda> findByNombreContainingIgnoreCase(String nombre);

    List<Tienda> findByRegion(String region);

    List<Tienda> findByComuna(String comuna);

    List<Tienda> findByRegionAndActivaTrue(String region);
}
