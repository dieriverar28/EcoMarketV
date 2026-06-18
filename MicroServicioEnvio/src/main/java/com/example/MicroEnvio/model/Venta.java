package com.example.MicroEnvio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private int id;

    @Column(name = "id_pedido")
    private Integer id_pedido;

    @Column(name = "id_tienda")
    private Integer id_tienda;

    @Column(name = "id_cliente")
    private Integer id_cliente;

    @NotNull(message = "La fecha de venta es obligatoria")
    @Column(name = "fecha_venta", nullable = false)
    private Date fecha_venta;

    @Column(name = "total_neto", nullable = false)
    private int total_neto;

    @Column(name = "descuento_aplicado")
    private int descuento_aplicado;

    @Column(name = "tipo_documento", length = 50)
    private String tipo_documento;
}
