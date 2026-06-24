package com.example.MicroCliente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClienteDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        private String nombre;

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 8, max = 15, message = "El teléfono debe tener entre 8 y 15 caracteres")
        private String telefono;

        @NotBlank(message = "La comuna es obligatoria")
        private String comuna;

        @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
        private String direccion_envio;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        private String email;

        @NotNull(message = "El género es obligatorio")
        @Min(value = 1, message = "El ID de género debe ser mayor a 0")
        private Integer id_genero;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_cliente;
        private String nombre;
        private String telefono;
        private String comuna;
        private String direccion_envio;
        private String email;
        private String nombre_genero;
    }
}
