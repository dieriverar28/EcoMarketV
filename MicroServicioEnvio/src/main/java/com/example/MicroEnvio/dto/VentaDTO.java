package com.example.MicroEnvio.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

public class VentaDTO {
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Request {
        private Integer id_pedido;
        private Integer id_tienda;
        private Integer id_cliente;
        @NotNull(message = "La fecha de venta es obligatoria")
        private Date fecha_venta;
        @Min(value = 0, message = "El total neto no puede ser negativo")
        private int total_neto;
        private int descuento_aplicado;
        private String tipo_documento;
    }
    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private int id_venta;
        private Integer id_pedido;
        private Integer id_tienda;
        private Integer id_cliente;
        private Date fecha_venta;
        private int total_neto;
        private int descuento_aplicado;
        private String tipo_documento;
    }
}
