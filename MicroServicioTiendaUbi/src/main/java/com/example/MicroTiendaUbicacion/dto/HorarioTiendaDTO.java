package com.example.MicroTiendaUbicacion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

public class HorarioTiendaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Integer id_horario;

        @NotNull(message = "El id de la tienda es obligatorio")
        private Integer id_tienda;

        @NotNull(message = "El día de la semana es obligatorio")
        private String dia_semana;

        @NotNull(message = "La hora de apertura es obligatoria")
        private LocalTime hora_apertura;

        @NotNull(message = "La hora de cierre es obligatoria")
        private LocalTime hora_cierre;

        private Boolean cerrado = false;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_horario;
        private Integer id_tienda;
        private String dia_semana;
        private LocalTime hora_apertura;
        private LocalTime hora_cierre;
        private Boolean cerrado;
    }
}