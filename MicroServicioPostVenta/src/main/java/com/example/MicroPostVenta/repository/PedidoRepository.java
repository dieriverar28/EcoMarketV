package com.example.MicroPostVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.MicroPostVenta.model.Pedido;





public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido p")
    List<Pedido> obtenerPedidos();

    @Query("SELECT p FROM Pedido p WHERE p.id_pedido = :id_pedido")
    Pedido buscarPedido(int id_pedido);

}
