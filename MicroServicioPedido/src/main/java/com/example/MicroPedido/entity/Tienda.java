package com.example.MicroPedido.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tienda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una tienda")
public class Tienda {

    @Id
    @Schema(description = "Identificador único de la tienda", example = "1")
    private int id_tienda;

    @Column(name="nombre", nullable=false)
    @Schema(description = "Nombre de la tienda", example = "Tienda Central")
    private String nombre;

    @Column(name="direccion", nullable=false)
    @Schema(description = "Dirección de la tienda", example = "Av. Providencia 1234")
    private String direccion;

    @Column(name="id_comuna", nullable=false)
    @Schema(description = "Identificador de la comuna", example = "13101")
    private int id_comuna;
}