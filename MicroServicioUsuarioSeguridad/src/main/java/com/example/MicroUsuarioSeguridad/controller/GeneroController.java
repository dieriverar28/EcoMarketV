package com.example.MicroUsuarioSeguridad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MicroUsuarioSeguridad.dto.GeneroDTO;
import com.example.MicroUsuarioSeguridad.service.GeneroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/generos")
@Tag(name = "Genero", description = "Operaciones relacionadas con género")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    @Operation(summary = "Listar géneros", description = "Obtiene la lista de todos los géneros")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GeneroDTO.Response.class)))
    public ResponseEntity<List<GeneroDTO.Response>> listarGeneros() {
        return ResponseEntity.ok(generoService.listarGeneros());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener género por ID", description = "Obtiene un género según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Género encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneroDTO.Response.class))),
            @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })
    public ResponseEntity<GeneroDTO.Response> obtenerGeneroPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(generoService.obtenerGeneroPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear género", description = "Registra un nuevo género")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Género creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneroDTO.Response.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<GeneroDTO.Response> crearGenero(@Valid @RequestBody GeneroDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.crearGenero(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar género", description = "Actualiza un género existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Género actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GeneroDTO.Response.class))),
            @ApiResponse(responseCode = "404", description = "Género no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<GeneroDTO.Response> actualizarGenero(
            @PathVariable Integer id,
            @Valid @RequestBody GeneroDTO.Request request) {
        return ResponseEntity.ok(generoService.actualizarGenero(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar género", description = "Elimina un género por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Género eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })
    public ResponseEntity<Void> eliminarGenero(@PathVariable Integer id) {
        generoService.eliminarGenero(id);
        return ResponseEntity.noContent().build();
    }
}