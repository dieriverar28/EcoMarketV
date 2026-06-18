package com.example.MicroCliente.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "id_comuna", nullable = false)
    private Integer id_comuna;

    @Column(name = "direccion_envio")
    private String direccion_envio;

    @ManyToOne
    @JoinColumn(name = "id_genero", nullable = false)
    private Genero genero;
}