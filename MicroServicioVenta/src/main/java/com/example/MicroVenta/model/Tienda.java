package com.example.MicroVenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private int id_tienda;
    @Column(name="nombre", nullable=false)
    private String nombre;
    @Column(name="direccion", nullable=false)
    private String direccion;
    @Column(name="id_comuna", nullable=false)
    private int comuna;
    @Column(name="id_region", nullable=false)
    private int region;

}
