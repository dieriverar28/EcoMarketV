package com.example.MicroPedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.MicroPedido.entity.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Integer> {

    @Query("SELECT t FROM Tienda t")
    List<Tienda> obtenerTiendas();

    @Query("SELECT t FROM Tienda t WHERE t.id_tienda = :id_tienda")
    Tienda buscarTienda(@Param("id_tienda") int id_tienda);

}
