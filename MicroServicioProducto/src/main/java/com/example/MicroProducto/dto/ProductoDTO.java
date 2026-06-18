package com.example.MicroProducto.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        private String descripcion;

        @NotBlank(message = "La categoría es obligatoria")
        private String categoria;

        @Min(value = 0, message = "El precio base no puede ser negativo")
        private int precio_base;

        private boolean estado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String nombre;
        private String descripcion;
        private String categoria;
        private int precio_base;
        private boolean estado;
    }
}
