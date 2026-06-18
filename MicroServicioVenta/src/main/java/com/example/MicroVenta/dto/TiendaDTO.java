package com.example.MicroVenta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TiendaDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

       @NotNull(message = "El ID de la tienda es obligatorio")
        private Integer id_tienda;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotBlank(message = "La direccion es obligatoria")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "La direccion debe contener al menos 2 palabras")
        private String direccion;

        @NotNull(message = "El ID de la comuna es obligatorio")
        private Integer id_comuna;

        @NotNull(message = "El ID de la región es obligatorio")
        private Integer id_region;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_tienda;
        private String nombre;
        private String direccion;
        private Integer id_comuna;
        private Integer id_region;
    }

}
