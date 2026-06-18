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

        // id_cliente no va en el Request, el ID lo genera la base de datos

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        private String nombre;

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 8, max = 15, message = "El teléfono debe tener entre 8 y 15 caracteres")
        private String telefono;

        @NotNull(message = "La comuna es obligatoria")
        @Min(value = 1, message = "El ID de comuna debe ser mayor a 0")
        private Integer id_comuna;

        @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
        private String direccion_envio; // nullable en la entidad, no es obligatorio

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
        private Integer id_comuna;
        private String direccion_envio;
        private String nombre_genero; // se devuelve el nombre, no solo el ID
    }
}