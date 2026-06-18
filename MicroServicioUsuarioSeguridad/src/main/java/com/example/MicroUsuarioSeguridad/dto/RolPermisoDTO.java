
package com.example.MicroUsuarioSeguridad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RolPermisoDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        @NotNull(message = "El id del rol es obligatorio")
        private Integer id_rol;

        @NotNull(message = "El id del permiso es obligatorio")
        private Integer id_permiso;

        @NotBlank(message = "El nombre del rol es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre del rol debe tener entre 3 y 50 caracteres")
        private String nombre_rol;

        @NotBlank(message = "El módulo es obligatorio")
        @Size(min = 3, max = 50, message = "El módulo debe tener entre 3 y 50 caracteres")
        private String modulo;

        @NotBlank(message = "La acción es obligatoria")
        @Size(min = 3, max = 50, message = "La acción debe tener entre 3 y 50 caracteres")
        private String accion;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Integer id;
        private Integer id_rol;
        private Integer id_permiso;
        private String nombre_rol;
        private String modulo;
        private String accion;
    }
}