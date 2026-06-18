package com.example.MicroPostVenta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProductoDTO {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El ID de la reseña es obligatorio")
        private int id_producto;

        @NotNull(message = "El cliente es obligatorio")
        private String nombre;

        @NotNull(message = "La descripción es obligatoria")
        private String descripcion;

        @NotNull(message = "La categoría es obligatoria")
        private String categoria;

        @NotNull(message = "El precio base es obligatorio")
        private int  precio_base;

        @NotNull(message = "El estado es obligatorio")
        private boolean estado;


    
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
    private int id_producto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private int precio_base;
    private boolean estado;
    }

}
