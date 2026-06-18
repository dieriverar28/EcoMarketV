package com.example.MicroPedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

        @NotBlank(message = "La dirección es obligatoria")
        private String direccion;

        @Min(value = 1, message = "El ID de comuna debe ser mayor a 0")
        private int id_comuna;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private int id_tienda;
        private String nombre;
        private String direccion;
        private int id_comuna;
    }
}