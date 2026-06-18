package com.example.MicroVenta.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClienteDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El ID del cliente es obligatorio")
        private Integer id_cliente;

        @NotBlank(message = "El nombre es obligatorio")
        @Pattern(regexp = "^[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}\\s+[\\w\\sáéíóúÁÉÍÓÚñÑ]{2,}.*$",
                 message = "El nombre debe contener al menos 2 palabras")
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Pattern(regexp = "^[^@\s]+@[^@\s]+\\.[^@\s]+$",
                 message = "El email debe tener un formato válido")
        private String email;

        @NotNull(message = "El ID del género es obligatorio")
        private Integer generoId;

        @NotNull(message = "El ID de la comuna es obligatorio")
        private Integer id_comuna;

        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;

        @NotBlank(message = "La dirección de envío es obligatoria")
        private String direccion_envio;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_cliente;
        private String nombre;
        private String email;
        private String telefono;
        private Integer comuna;
        private String direccion_envio;
        private Integer genero;
    }

}
