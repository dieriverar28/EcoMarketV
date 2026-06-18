package com.example.MicroPedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DetallePedidoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El ID del producto es obligatorio")
        @Min(value = 1, message = "El ID del producto debe ser mayor a 0")
        private Integer id_producto;

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private Integer cantidad;

        @NotNull(message = "El precio unitario es obligatorio")
        @Min(value = 0, message = "El precio unitario no puede ser negativo")
        private Integer precio_unitario;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_detalle;
        private Integer id_pedido;
        private Integer id_producto;
        private Integer cantidad;
        private Integer precio_unitario;
    }
}