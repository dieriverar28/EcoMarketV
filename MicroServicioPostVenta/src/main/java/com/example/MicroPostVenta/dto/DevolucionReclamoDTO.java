package com.example.MicroPostVenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.Producto;
import com.example.MicroPostVenta.entity.Venta;

import jakarta.validation.constraints.NotNull;

public class DevolucionReclamoDTO {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El ID de la devolución es obligatorio")
        private int id_devolucion;

        @NotNull(message = "La venta es obligatoria")
        private Integer venta;

        @NotNull(message = "El cliente es obligatorio")
        private Integer cliente;

        @NotNull(message = "El producto es obligatorio")
        private Integer producto;

        @NotNull(message = "El motivo es obligatorio")
        private String motivo;

        @NotNull(message = "El estado es obligatorio")
        private boolean estado;

        @NotNull(message = "La fecha es obligatoria")
        private Data fecha;
    
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
    private int id_devolucion;
    private Venta venta;
    private Cliente cliente;
    private Producto producto;
    private String motivo;
    private boolean estado;
    private Data fecha;
    }

}

