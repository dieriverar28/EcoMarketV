package com.example.MicroPostVenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id; 
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//se conecta con entidad
@Table(name = "cliente")//nombre de la tabla en la base de dstos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    private int id_cliente;
    @Column(name="nombre", nullable=false)
    private String nombre;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="telefono", nullable=false)
    private String telefono;
    @Column(name="id_comuna", nullable=false)
    private int comuna;
    private String direccion_envio;
    @Column(name="id_genero", nullable=false)
    private int genero;

}
