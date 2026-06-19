package com.example.MicroPostVenta.dto;

import java.sql.Date;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.CuponDescuento;

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
        private int tienda;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_pedido;
        private Cliente cliente;
        private int tienda;
        private boolean estado;
        private CuponDescuento cuponDescuento;
        private Date fecha_pedido;

    }
}

