package com.example.MicroCliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genero")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_genero;

    @NotBlank(message = "El nombre del género es obligatorio")
    @Column(name = "nombre_genero", nullable = false, unique = true, length = 50)
    private String nombre_genero;
}