package com.example.MicroPostVenta.dto;




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
        private String nombre;

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del email no es válido")
        private String email;

        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;

        @NotNull(message = "La comuna es obligatoria")
        private Integer comuna;

        @NotBlank(message = "La dirección de envío es obligatoria")
        private String direccion_envio;

        @NotNull(message = "El género es obligatorio")
        private Integer genero;
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
