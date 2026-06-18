package com.example.MicroPedido.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private int id_pedido;

    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="id_tienda", nullable=false)
    private Tienda tienda;

    @Column(name="estado", nullable=false)
    private String estado;

    @Column(name="id_cupon", nullable=true)
    private Integer id_cupon;

    @Column(name="fecha_pedido", nullable=false)
    private Date fecha_pedido;

    @Column(name="total", nullable=false)
    private int total;
}