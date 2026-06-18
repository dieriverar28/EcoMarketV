package com.example.MicroUsuarioSeguridad.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private int id_usuario;

    @ManyToOne
    @JoinColumn(name="id_genero", nullable=false)
    private Genero genero;

    @Column(name="nombre", nullable=false)
    private String nombre;

    @Column(name="correo", nullable=false)
    private String correo;

    @Column(name="telefono", nullable=false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name="id_rol", nullable=false)
    private RolPermiso rol;

    @Column(name="id_tienda", nullable=false)
    private int idTienda;

    @Column(name="estado", nullable=false)
    private boolean estado;
}