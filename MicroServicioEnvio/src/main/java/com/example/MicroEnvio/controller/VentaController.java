package com.example.MicroEnvio.controller;

import com.example.MicroEnvio.dto.RutaEntregaDTO;
import com.example.MicroEnvio.dto.VentaDTO;
import com.example.MicroEnvio.service.VentaService;

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
@RequestMapping("/api/v1/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
        @Operation(summary = "Obtener Ventas",description = "Obtener lista de ventas")
    public ResponseEntity<List<VentaDTO.Response>> listarTodos() {
        return ResponseEntity.ok(ventaService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Venta por ID",description = "Obtener venta por su ID")
    public ResponseEntity<VentaDTO.Response> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(ventaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar Venta",description = "Registra venta existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = VentaDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public ResponseEntity<VentaDTO.Response> crear(@Valid @RequestBody VentaDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Venta",description = "Actualiza venta existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta actualizada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = VentaDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public ResponseEntity<VentaDTO.Response> actualizar(@PathVariable int id, @Valid @RequestBody VentaDTO.Request request) {
        return ResponseEntity.ok(ventaService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Venta",description = "Elimina venta por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
