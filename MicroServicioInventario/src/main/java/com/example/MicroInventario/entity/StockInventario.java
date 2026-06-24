package com.example.MicroInventario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="stockinventario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_stock;

    @ManyToOne
    @JoinColumn(name="id_tienda", nullable=false)
    private Tienda tienda;

    @ManyToOne
    @JoinColumn(name="id_producto", nullable=false)
    private Producto producto;

    @Column(name="cantidad", nullable=false)
    private Integer cantidad;

    @Column(name="stock_min")
    private Integer stock_min;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

}
