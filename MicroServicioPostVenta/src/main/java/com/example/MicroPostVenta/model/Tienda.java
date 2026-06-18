package com.example.MicroPostVenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private int id_tienda;
    @Column(name="nombre", nullable=false)
    private String nombre;
    @Column(name="direccion", nullable=false)
    private String direccion;
    @Column(name="comuna", nullable=false)
    private int comuna;
    @Column(name="region", nullable=false)
    private int region;

}
