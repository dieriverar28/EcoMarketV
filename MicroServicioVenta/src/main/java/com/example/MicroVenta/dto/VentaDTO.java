package com.example.MicroVenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

public class VentaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_venta;
        private Date fecha_venta;
        private int total_neto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Integer id_pedido;
        private Integer id_tienda;
        private Integer id_cliente;
        private Date fecha_venta;
        private int total_neto;
        private int descuento_aplicado;
        private String tipo_documento;
    }

}
