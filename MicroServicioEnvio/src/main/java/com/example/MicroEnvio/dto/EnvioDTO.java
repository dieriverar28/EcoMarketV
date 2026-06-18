package com.example.MicroEnvio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

public class EnvioDTO {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El id de venta es obligatorio")
        private Integer id_venta;
        @NotNull(message = "El id de cliente es obligatorio")
        private Integer id_cliente;
        @NotNull(message = "El id de proveedor es obligatorio")
        private Integer id_provedor;
        @NotNull(message = "El id de ruta es obligatorio")
        private Integer id_ruta;
        private boolean estado;
        @NotNull(message = "La fecha de despacho es obligatoria")
        private Date fecha_despacho;
        @NotNull(message = "La fecha de entrega estimada es obligatoria")
        private Date fecha_entrega_est;
    }

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_envio;
        private int id_venta;
        private String nombre_cliente;
        private String nombre_proveedor;
        private String ruta_descripcion;
        private boolean estado;
        private Date fecha_despacho;
        private Date fecha_entrega_est;
    }
}
