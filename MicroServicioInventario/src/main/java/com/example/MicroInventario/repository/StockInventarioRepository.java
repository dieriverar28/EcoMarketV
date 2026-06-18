package com.example.MicroInventario.repository;
import org.springframework.stereotype.Repository;

import com.example.MicroInventario.entity.StockInventario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface StockInventarioRepository extends JpaRepository<StockInventario, Integer> {

    List<StockInventario> findByEstado(boolean estado);

    @Query("SELECT s FROM StockInventario s WHERE s.cantidad < s.stock_min")
    List<StockInventario> buscarStockBajo();

}

