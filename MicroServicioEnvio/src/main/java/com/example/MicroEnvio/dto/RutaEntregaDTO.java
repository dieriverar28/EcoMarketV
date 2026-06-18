package com.example.MicroEnvio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RutaEntregaDTO {
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotBlank(message = "La descripción es obligatoria")
        private String descripcion;
    }
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_ruta;
        private String descripcion;
    }
}
