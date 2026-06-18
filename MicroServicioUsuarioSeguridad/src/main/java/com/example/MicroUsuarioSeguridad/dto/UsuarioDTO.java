package com.example.MicroUsuarioSeguridad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UsuarioDTO {

    // --- ESTO ES LO QUE ENTRA DESDE EL FRONTEND (REQUEST) ---
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        @NotNull(message = "El género es obligatorio")
        private Integer id_genero;

        @NotBlank(message = "El nombre es obligatorio")
        private String nombre;

        @NotBlank(message = "El correo es obligatorio")
        private String correo;

        @NotBlank(message = "El teléfono es obligatorio")
        private String telefono;

        @NotNull(message = "El rol es obligatorio")
        private Integer id_rol;

        @NotNull(message = "La tienda es obligatoria")
        private Integer id_tienda;

        private Boolean estado;
    }

    // --- ESTO ES LO QUE RESPONDE TU API (RESPONSE) ---
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Integer id_usuario;
        private String nombre;
        private String correo;
        private String telefono;
        private Integer id_genero;
        private Integer id_rol;
        private Integer id_tienda;
        private Boolean estado;
    }

    public String getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

    public String getCorreo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCorreo'");
    }

    public String getTelefono() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTelefono'");
    }

    public int getId_tienda() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId_tienda'");
    }

    public boolean getEstado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstado'");
    }
}