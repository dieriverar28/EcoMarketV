package com.example.MicroUsuarioSeguridad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rolpermiso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolPermiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_rol", nullable = false)
    private Integer id_rol;

    @Column(name = "id_permiso", nullable = false)
    private Integer id_permiso;

    @Column(name = "nombre_rol", nullable = false)
    private String nombre_rol;

    @Column(name = "modulo", nullable = false)
    private String modulo;

    @Column(name = "accion", nullable = false)
    private String accion;


}