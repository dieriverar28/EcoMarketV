package com.example.MicroVenta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import com.example.MicroVenta.entity.Venta;

public class BoletaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El ID de la boleta es obligatorio")
        private int id_Boleta;

        @NotNull(message = "La decripcion de la venta es obligatorio")
        private Venta venta;

        @NotNull(message = "El folio es obligatoria")
        private String folio;

        @NotNull(message = "El timbre electronico es obligatorio")
        private String timbre_electronico;

        @NotNull(message = "El nombre receptor es obligatorio")
        private String nombre_receptor;

        @NotNull(message = "La ruta del receptor es obligatoria")
        private String ruta_receptor;

        @NotNull(message = "La fecha de emision es obligatoria")
        private Date fecha_emision;

        @NotNull(message = "El monto neto es obligatorio")
        private int monto_neto;

        @NotNull(message = "El monto iva es obligatorio")
        private int monto_iva;

        @NotNull(message = "El monto total es obligatorio")
        private int monto_total;

        @NotNull(message = "El email de envio debe ser obligatorio")
        private String email_envio;

        @NotNull(message = "La anulada es obigatorio")
        private Boolean anulada;


    }

    /**
     * La respuesta incluye el GeneroDTO completo obtenido desde ms-genero via Feign.
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_Boleta;
        private Venta venta;
        private String folio;
        private String timbre_electronico;
        private String nombre_receptor;
        private String ruta_receptor;
        private Date fecha_emision;
        private Integer monto_neto;
        private Integer monto_iva;
        private Integer monto_total;
        private String email_envio;
        private Boolean anulada;
    }

}

