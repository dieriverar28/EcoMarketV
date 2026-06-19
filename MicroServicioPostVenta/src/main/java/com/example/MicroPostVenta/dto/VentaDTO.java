package com.example.MicroPostVenta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.Pedido;
import com.example.MicroPostVenta.entity.Tienda;

public class VentaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El ID de la venta es obligatorio")
        private Integer id_venta;

        @NotNull(message = "El pedido es obligatorio")
        private Pedido pedido;

        @NotNull(message = "La tienda es obligatoria")
        private Tienda tienda;

        @NotNull(message = "El cliente es obligatorio")
        private Cliente cliente;

        @NotNull(message = "La fecha de venta es obligatoria")
        private Date fecha_venta;

        @Min(value = 0, message = "El total neto no puede ser negativo")
        private Integer total_neto;

        @Min(value = 0, message = "El descuento aplicado no puede ser negativo")
        private Integer descuento_aplicado;

        @NotBlank(message = "El tipo de documento es obligatorio")
        @Size(min = 3, max = 50, message = "El tipo de documento debe tener entre 3 y 50 caracteres")
        private String tipo_documento;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id_venta;
        private Pedido pedido;
        private Tienda tienda;
        private Cliente cliente;
        private Date fecha_venta;
        private Integer total_neto;
        private Integer descuento_aplicado;
        private String tipo_documento;
    }

}

