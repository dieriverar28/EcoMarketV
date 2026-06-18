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
    @Column(name = "id_region", nullable = false)
    private Integer idRegion;

    @Column(name = "id_comuna", nullable = false)
    private Integer idComuna;
    @Column(name = "codigo_postal", length = 10)
    private String codigo_postal;

    @Column(name = "activa", nullable = false)
    private Boolean activa = true;

// Una tienda puede tener MUCHOS horarios (lunes, martes, etc.)
// mappedBy = "tienda"  → le dice que la relación la maneja HorarioTienda (evita columna extra)
// cascade = ALL        → si eliminas la tienda, se eliminan sus horarios también
// fetch = LAZY         → los horarios NO se cargan de la BD hasta que los necesites (mejor rendimiento)
    @OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HorarioTienda> horarios;   
}