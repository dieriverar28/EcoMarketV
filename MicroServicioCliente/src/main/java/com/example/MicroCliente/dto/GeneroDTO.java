package com.example.MicroCliente.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GeneroDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        // id_genero no va en el Request, el ID lo genera la base de datos

        @NotBlank(message = "El nombre del género es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre del género debe tener entre 3 y 50 caracteres")
        private String nombre_genero;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_genero;
        private String nombre_genero;
    }
}