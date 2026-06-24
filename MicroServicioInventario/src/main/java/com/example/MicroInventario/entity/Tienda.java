package com.example.MicroInventario.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity //se conecta con entidad
@Table(name="tienda") //la tabla nombre persona
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tienda;

    @Column(name="nombre", nullable=false)
    private String nombre;

    @Column(name="direccion", nullable=false)
    private String direccion;

    @Column(name="comuna", nullable=false)
    private String comuna;
    
    @Column(name="region", nullable=false)
    private String region;
}
