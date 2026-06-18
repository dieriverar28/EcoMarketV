package com.example.MicroVenta.dto;

import java.sql.Date;

import com.example.MicroVenta.model.Cliente;
import com.example.MicroVenta.model.CuponDescuento;
import com.example.MicroVenta.model.Tienda;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PedidoDTO {
    
        @NotNull(message = "El ID del pedido es obligatorio")
        private int id_pedido;

        @NotNull(message = "El cliente es obligatorio")
        private Cliente cliente;

        @NotNull(message = "La tienda es obligatoria")
        private Tienda tienda;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_pedido;
        private Cliente cliente;
        private Tienda tienda;
        private boolean estado;
        private CuponDescuento cuponDescuento;
        private Date fecha_pedido;

    }























}