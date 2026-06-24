package com.example.MicroTiendaUbicacion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tienda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tienda;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "comuna", nullable = false, length = 100)
    private String comuna;

    @Column(name = "region", nullable = false, length = 100)
    private String region;

    @Column(name = "codigo_postal", length = 10)
    private String codigo_postal;

    @Column(name = "activa", nullable = false)
    private Boolean activa = true;

    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HorarioTienda> horarios;   
}
