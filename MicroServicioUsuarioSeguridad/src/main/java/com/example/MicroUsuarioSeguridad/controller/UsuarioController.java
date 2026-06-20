package com.example.MicroUsuarioSeguridad.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;
import com.example.MicroUsuarioSeguridad.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuario Controller", description = "Endpoints para la gestión y seguridad de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista con todos los usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    public ResponseEntity<List<UsuarioDTO.Response>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Devuelve un único usuario basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioDTO.Response> buscarPorId(
            @Parameter(description = "ID del usuario a buscar", example = "1") 
            @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Registra un usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    public ResponseEntity<UsuarioDTO.Response> guardar(@Valid @RequestBody UsuarioDTO.Request request) {
        return new ResponseEntity<>(usuarioService.guardar(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario existente", description = "Modifica los datos de un usuario por su ID")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito")
    public ResponseEntity<UsuarioDTO.Response> actualizar(
            @Parameter(description = "ID del usuario a modificar", example = "1") 
            @PathVariable Integer id, 
            @Valid @RequestBody UsuarioDTO.Request request) {
        return ResponseEntity.ok(usuarioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema por su ID")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del usuario a eliminar", example = "1") 
            @PathVariable Integer id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
