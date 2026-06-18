package com.example.MicroEnvio.controller;

import com.example.MicroEnvio.dto.ProveedorDTO;
import com.example.MicroEnvio.dto.RutaEntregaDTO;
import com.example.MicroEnvio.service.RutaEntregaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rutas")
@RequiredArgsConstructor
public class RutaEntregaController {

    private final RutaEntregaService rutaEntregaService;

    @GetMapping
        @Operation(summary = "Obtener Rutas de Entrega",description = "Obtener lista de rutas de entrega")

    public ResponseEntity<List<RutaEntregaDTO.Response>> listarTodos() {
        return ResponseEntity.ok(rutaEntregaService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Ruta de Entrega por ID",description = "Obtener ruta de entrega por su ID")
    public ResponseEntity<RutaEntregaDTO.Response> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(rutaEntregaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar Ruta de Entrega",description = "Registra ruta de entrega existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ruta de entrega registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = RutaEntregaDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "ruta de entrega no encontrada")
        })
    public ResponseEntity<RutaEntregaDTO.Response> crear(@Valid @RequestBody RutaEntregaDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rutaEntregaService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Ruta de Entrega",description = "Actualiza ruta de entrega existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ruta de entrega actualizada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = RutaEntregaDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "ruta de entrega no encontrada")
        })
    public ResponseEntity<RutaEntregaDTO.Response> actualizar(@PathVariable int id, @Valid @RequestBody RutaEntregaDTO.Request request) {
        return ResponseEntity.ok(rutaEntregaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Ruta de Entrega",description = "Elimina ruta de entrega por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ruta de entrega eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "ruta de entrega no encontrada")
        })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        rutaEntregaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
