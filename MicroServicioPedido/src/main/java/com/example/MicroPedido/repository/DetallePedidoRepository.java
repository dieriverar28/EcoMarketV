package com.example.MicroPedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroPedido.entity.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    // OBTENER TODOS
    @Query("SELECT d FROM DetallePedido d")
    List<DetallePedido> obtenerDetallePedidos();

    // BUSCAR POR id_detalle
    @Query("SELECT d FROM DetallePedido d WHERE d.id_detalle = :id_detalle")
    DetallePedido buscarDetallePedido(@Param("id_detalle") int id_detalle);

    // ELIMINAR POR id_detalle
    @Modifying
    @Transactional
    @Query("DELETE FROM DetallePedido d WHERE d.id_detalle = :id_detalle")
    int eliminarDetallePedido(@Param("id_detalle") int id_detalle);

    // MODIFICAR
    @Modifying
    @Transactional
    @Query("UPDATE DetallePedido d SET d.cantidad = :cantidad, d.precio_unitario = :precio_unitario WHERE d.id_detalle = :id_detalle")
    int modificarDetallePedido(@Param("id_detalle") int id_detalle,
                               @Param("cantidad") int cantidad,
                               @Param("precio_unitario") int precio_unitario);
}
