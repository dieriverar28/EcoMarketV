package com.example.MicroEnvio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ClienteDTO {
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Email no válido")
        private String email;
        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;
        private String direccion_envio;
        @NotNull(message = "La comuna es obligatoria")
        private Integer id_comuna;
    }
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_cliente;
        private String nombre;
        private String email;
        private String telefono;
        private String direccion_envio;
        private int id_comuna;
    }
}
