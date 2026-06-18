package com.example.MicroPedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; //  necesario para @Param
import org.springframework.stereotype.Repository;

import com.example.MicroPedido.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("SELECT p FROM Pedido p")
    List<Pedido> obtenerPedidos();

    @Query("SELECT p FROM Pedido p WHERE p.id_pedido = :id_pedido")
    Pedido buscarPedido(@Param("id_pedido") int id_pedido); //  @Param agregado

}