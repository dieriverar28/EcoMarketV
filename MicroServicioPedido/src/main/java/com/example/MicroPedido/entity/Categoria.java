package com.example.MicroPedido.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una categoría de productos")
public class Categoria {

    @Id
    @Schema(description = "Identificador único de la categoría", example = "1")
    private int id_categoria;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre de la categoría", example = "Bebidas")
    private String nombre;

    @Column(name = "descripcion")
    @Schema(description = "Descripción de la categoría", example = "Productos líquidos para consumo")
    private String descripcion;

    @Column(name = "estado", nullable = false)
    @Schema(description = "Estado de la categoría", example = "true")
    private boolean estado;

}