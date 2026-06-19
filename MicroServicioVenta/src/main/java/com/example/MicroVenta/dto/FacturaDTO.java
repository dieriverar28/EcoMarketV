package com.example.MicroVenta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import com.example.MicroVenta.entity.Venta;

public class FacturaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El ID de la boleta es obligatorio")
        private int id_factura;

        @NotNull(message = "La decripcion de la venta es obligatorio")
        private Venta venta;

        @NotNull(message = "El folio es obligatoria")
        private String folio;

        @NotNull(message = "El timbre electronico es obligatorio")
        private String timbre_electronico;

        @NotNull(message = "El nombre receptor es obligatorio")
        private String razon_social;

        @NotNull(message = "La ruta del receptor es obligatoria")
        private String numrun_cliente;

        @NotNull(message = "La fecha de emision es obligatoria")
        private String dvrun_cliente;

        @NotNull(message = "El monto neto es obligatorio")
        private String giro;

        @NotNull(message = "El monto iva es obligatorio")
        private Date fecha_emision;

        @NotNull(message = "La anulada es obigatorio")
        private int monto_iva;

        @NotNull(message = "El monto total es obligatorio")
        private int monto_total;

        @NotNull(message = "El email de envio debe ser obligatorio")
        private String email_envio;


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_factura;
        private Venta venta;
        private String folio;
        private String timbre_electronico;
        private String razon_social;
        private String numrun_cliente;
        private String dvrun_cliente;
        private String giro;
        private Date fecha_emision;
        private Integer monto_iva;
        private Integer monto_total;
        private String email_envio;
        private Boolean anulada;
    }

}

