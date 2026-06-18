package com.example.MicroTiendaUbicacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MicroTiendaUbicacion.entity.HorarioTienda;

@Repository
public interface HorarioTiendaRepository extends JpaRepository<HorarioTienda, Integer> {

    @Query("SELECT h FROM HorarioTienda h WHERE h.tienda.id_tienda = :id_tienda")
    List<HorarioTienda> findByTienda(@Param("id_tienda") Integer id_tienda);

    List<HorarioTienda> findByCerradoFalse();

}