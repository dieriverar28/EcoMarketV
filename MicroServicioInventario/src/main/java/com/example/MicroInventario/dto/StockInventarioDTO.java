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
        private Integer id_tienda;

        @NotNull(message = "El producto es obligatorio")
        private Long id_producto;

        @Min(value = 0, message = "La cantidad no puede ser negativa")
        private Integer cantidad;

        @Min(value = 0, message = "El stock minimo no puede ser negativo")
        private Integer stock_min;

        @NotNull(message = "El estado es obligatorio")
        private boolean estado;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_stock;
        private Integer id_tienda;
        private Long id_producto;
        private Integer cantidad;
        private Integer stock_min;
        private boolean estado;
    }
}
