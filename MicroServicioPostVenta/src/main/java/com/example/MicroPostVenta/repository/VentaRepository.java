package com.example.MicroPostVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MicroPostVenta.model.Venta;

//import com.example.EcoMarketSPA.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT v FROM Venta v")
    List<Venta> obtenerVentas();

    @Query("SELECT v FROM Venta v WHERE v.id_venta = :id_venta")
    Venta buscarVenta(int id_venta);

}
