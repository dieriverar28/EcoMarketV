package com.example.MicroPedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        private String nombre;

        @Min(value = 1, message = "El ID de categoría debe ser mayor a 0")
        private int id_categoria;

        @Min(value = 0, message = "El precio base no puede ser negativo")
        private int precio_base;

        private boolean estado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private int id_producto;
        private String nombre;
        private int id_categoria;
        private String nombre_categoria;
        private int precio_base;
        private boolean estado;
    }
}