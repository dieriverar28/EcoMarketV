package com.example.MicroInventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StockInventarioDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "La tienda es obligatoria")
        private int id_tienda;

        @NotNull(message = "El producto es obligatorio")
        private int id_producto;

        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private int cantidad;

        @Min(value = 0, message = "El stock minimo no puede ser negativo")
        private int stock_min;

        @NotNull(message = "El estado es obligatorio")
        private boolean estado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private int id_stock;
        private int id_tienda;
        private int id_producto;
        private int cantidad;
        private int stock_min;
        private boolean estado;
    }
}