package com.example.MicroEnvio.controller;

import com.example.MicroEnvio.dto.EnvioDTO;
import com.example.MicroEnvio.dto.ProveedorDTO;
import com.example.MicroEnvio.service.ProveedorService;

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
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
        @Operation(summary = "Obtener Proveedores",description = "Obtener lista de proveedores")

    public ResponseEntity<List<ProveedorDTO.Response>> listarTodos() {
        return ResponseEntity.ok(proveedorService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Proveedor por ID",description = "Obtener proveedor por su ID")
    public ResponseEntity<ProveedorDTO.Response> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(proveedorService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar Proveedor",description = "Registra proveedor existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "proveedor registrado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = ProveedorDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "proveedor no encontrado")
        })
    public ResponseEntity<ProveedorDTO.Response> crear(@Valid @RequestBody ProveedorDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Proveedor",description = "Actualiza proveedor existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "proveedor actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = ProveedorDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "proveedor no encontrado")
        })
    public ResponseEntity<ProveedorDTO.Response> actualizar(@PathVariable int id, @Valid @RequestBody ProveedorDTO.Request request) {
        return ResponseEntity.ok(proveedorService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Proveedor",description = "Elimina proveedor por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "proveedor eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "proveedor no encontrado")
        })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
