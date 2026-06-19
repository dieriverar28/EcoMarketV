package com.example.MicroPedido.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un cliente")
public class Cliente {

    @Id
    @Schema(description = "Identificador único del cliente", example = "1")
    private int id_cliente;

    @Column(name="nombre", nullable=false)
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
    private String nombre;

    @Column(name="telefono", nullable=false)
    @Schema(description = "Teléfono del cliente", example = "987654321")
    private String telefono;

    @Column(name="id_comuna", nullable=false)
    @Schema(description = "Identificador de la comuna", example = "13101")
    private int id_comuna;

    @Column(name="direccion_envio")
    @Schema(description = "Dirección de envío del cliente", example = "Av. Providencia 1234")
    private String direccion_envio;

}
