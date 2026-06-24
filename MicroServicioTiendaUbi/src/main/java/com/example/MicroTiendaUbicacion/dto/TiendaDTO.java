package com.example.MicroTiendaUbicacion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TiendaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Integer id_tienda;

        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @NotBlank(message = "La dirección es obligatoria")
        private String direccion;

        private String telefono;

        @NotBlank(message = "La comuna es obligatoria")
        private String comuna;

        @NotBlank(message = "La región es obligatoria")
        private String region;

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
        private String comuna;
        private String region;
        private String codigo_postal;
        private Boolean activa;
        private List<HorarioTiendaDTO.Response> horarios;
    }
}
