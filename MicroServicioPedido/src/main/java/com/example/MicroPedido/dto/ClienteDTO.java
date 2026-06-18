package com.example.MicroPedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClienteDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El ID del cliente es obligatorio")
        @Min(value = 1, message = "El ID del cliente debe ser mayor a 0")
        private int id_cliente;

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        private String nombre;

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 8, max = 15, message = "El teléfono debe tener entre 8 y 15 caracteres")
        private String telefono;

        @NotNull(message = "La comuna es obligatoria")
        @Min(value = 1, message = "El ID de comuna debe ser mayor a 0")
        private int id_comuna;

        @NotBlank(message = "La dirección de envío es obligatoria")
        @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
        private String direccion_envio;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_cliente;
        private String nombre;
        private String telefono;
        private int id_comuna;
        private String direccion_envio;
    }
}