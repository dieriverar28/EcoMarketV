package com.example.MicroPostVenta.dto;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.Producto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class ResenaCalificacionDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El ID de la reseña es obligatorio")
        private int id_resena;

        @NotNull(message = "El cliente es obligatorio")
        private Integer cliente;

        @NotNull(message = "El producto es obligatorio")
        private Integer producto;

        @NotNull(message = "El cliente es obligatorio")
        private boolean estado;

        @NotNull(message = "El cliente es obligatorio")
        private Data fecha;
    
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
    private int id_resena;
    private Cliente cliente;
    private Producto producto;
    private String motivo;
    private boolean estado;
    private Data fecha;
    }

}

