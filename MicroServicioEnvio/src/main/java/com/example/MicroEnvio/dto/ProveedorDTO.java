package com.example.MicroEnvio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ProveedorDTO {
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;
    }
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_proveedor;
        private String nombre;
    }
}
