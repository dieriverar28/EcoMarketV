package com.example.MicroPostVenta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CuponDescuentoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
//filtracion de informacion
        @NotBlank(message = "El RUT es obligatorio")
        @Size(min = 3, max = 10, message = "El ID debe tener entre 3 y 10 caracteres")
        private int id_cupon_descuento;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El email debe contener al menos 2 palabras")
        private String email;

        @NotNull(message = "El ID del género es obligatorio")
        private Long generoId;
    }

  
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private int id_cupon_descuento;
        private int codigo;
        private int descuento_pct;
        private int descuento_monto;
        private Data fecha_expiracion;
        private boolean activo;
        
    }

}
