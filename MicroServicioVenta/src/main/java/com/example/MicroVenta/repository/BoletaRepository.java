package com.example.MicroVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.MicroVenta.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Integer>{

    @Query("SELECT b FROM Boleta b")
    List<Boleta> obtenerBoletas();

    @Query("SELECT b FROM Boleta b WHERE b.id_cliente = :id_cliente")
    Boleta buscarBoleta(int id_cliente);
}
