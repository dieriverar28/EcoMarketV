package com.example.MicroInventario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TiendaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        private String nombre;

        @NotBlank(message = "La direccion es obligatoria")
        @Size(min = 5, max = 150, message = "La direccion debe tener entre 5 y 150 caracteres")
        private String direccion;

        @NotNull(message = "La comuna es obligatoria")
        private String comuna;

        @NotNull(message = "La region es obligatoria")
        private String region;

    
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_tienda;
        private String nombre;
        private String direccion;
        private String comuna;
        private String region;
    }
}