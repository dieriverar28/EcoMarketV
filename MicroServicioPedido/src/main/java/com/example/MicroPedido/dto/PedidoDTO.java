package com.example.MicroPedido.dto;

import java.sql.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PedidoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @Min(value = 1, message = "El ID del cliente debe ser mayor a 0")
        private int id_cliente;

        @Min(value = 1, message = "El ID de la tienda debe ser mayor a 0")
        private int id_tienda;

        @NotBlank(message = "El estado es obligatorio")
        @Size(min = 3, max = 50, message = "El estado debe tener entre 3 y 50 caracteres")
        private String estado;

        private Integer id_cupon;

        @NotNull(message = "La fecha del pedido es obligatoria")
        private Date fecha_pedido;

        @Min(value = 0, message = "El total no puede ser negativo")
        private int total;

        @NotEmpty(message = "El pedido debe tener al menos un producto")
        @Valid
        private List<DetallePedidoDTO.Request> detalles;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_pedido;
        private int id_cliente;
        private int id_tienda;
        private String estado;
        private int id_cupon; // Se mantiene Integer porque puede ser null
        private Date fecha_pedido;
        private int total;
        private List<DetallePedidoDTO.Response> detalles;
    }
}