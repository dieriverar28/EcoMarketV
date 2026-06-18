package com.example.MicroEnvio.controller;

import com.example.MicroEnvio.dto.ClienteDTO;
import com.example.MicroEnvio.dto.EnvioDTO;
import com.example.MicroEnvio.service.EnvioService;

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
@RequestMapping("/api/v1/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping
        @Operation(summary = "Obtener Envios",description = "Obtener lista de envios")

    public ResponseEntity<List<EnvioDTO.Response>> listarTodos() {
        return ResponseEntity.ok(envioService.listarTodos());
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtener Envios Activos",description = "Obtener lista de envios activos")
    public ResponseEntity<List<EnvioDTO.Response>> listarActivos() {
        return ResponseEntity.ok(envioService.listarActivos());
    }

    @GetMapping("/cliente/{id_cliente}")
    @Operation(summary = "Obtener Envios por Cliente",description = "Obtener lista de envios por cliente")
    public ResponseEntity<List<EnvioDTO.Response>> listarPorCliente(@PathVariable int id_cliente) {
        return ResponseEntity.ok(envioService.listarPorCliente(id_cliente));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Envio por ID",description = "Obtener envio por su ID")
    public ResponseEntity<EnvioDTO.Response> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(envioService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar Envio",description = "Registra envio existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "envio registrado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = EnvioDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "envio no encontrado")
        })
    public ResponseEntity<EnvioDTO.Response> crear(@Valid @RequestBody EnvioDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Envio",description = "Actualiza envio existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "envio actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = EnvioDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "envio no encontrado")
        })
    public ResponseEntity<EnvioDTO.Response> actualizar(@PathVariable int id, @Valid @RequestBody EnvioDTO.Request request) {
        return ResponseEntity.ok(envioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Envio",description = "Elimina envio por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "envio eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "envio no encontrado")
        })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
