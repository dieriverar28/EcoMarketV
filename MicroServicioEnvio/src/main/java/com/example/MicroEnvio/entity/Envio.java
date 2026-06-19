package com.example.MicroEnvio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "envio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_provedor", nullable = false)
    private Proveedor provedor;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private RutaEntrega ruta;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @NotNull(message = "La fecha de despacho es obligatoria")
    @Column(name = "fecha_despacho", nullable = false)
    private Date fecha_despacho;

    @NotNull(message = "La fecha de entrega estimada es obligatoria")
    @Column(name = "fecha_entrega_est", nullable = false)
    private Date fecha_entrega_est;
}

