package com.example.MicroEnvio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "direccion_envio", length = 255)
    private String direccion_envio;

    @Column(name = "id_comuna", nullable = false)
    private int comuna;
}
