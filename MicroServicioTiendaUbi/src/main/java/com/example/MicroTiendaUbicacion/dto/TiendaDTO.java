package com.example.MicroTiendaUbicacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TiendaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        // En creación el ID no es obligatorio (lo genera la BD)
        private Integer id_tienda;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(
            regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
            message = "El nombre debe contener al menos 2 palabras"
        )
        private String nombre;

        @NotBlank(message = "La dirección es obligatoria")
        @Pattern(
            regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
            message = "La dirección debe contener al menos 2 palabras"
        )
        private String direccion;

        private String telefono;

        @NotNull(message = "El id de la comuna es obligatorio")
        private Integer id_comuna;

        @NotNull(message = "El id de la región es obligatorio")
        private Integer id_region;

        private String codigo_postal;

        private Boolean activa;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_tienda;
        private String nombre;
        private String direccion;
        private String telefono;
        private String codigo_postal;
        private Boolean activa;
        private Integer id_comuna;
        private Integer id_region;

        // Corregido
        private List<HorarioTiendaDTO.Response> horarios;
    }
}