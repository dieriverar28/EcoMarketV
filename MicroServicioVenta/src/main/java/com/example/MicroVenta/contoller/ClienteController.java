package com.example.MicroVenta.contoller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MicroVenta.dto.ClienteDTO;
import com.example.MicroVenta.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@Tag(name = "Cliente")
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener Clientes",description = "Obtener lista de clientes")
    public ResponseEntity<List<ClienteDTO.Response>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente",description = "Busca cliente por ID")
    public ResponseEntity<ClienteDTO.Response> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar Cliente",description = "Registra cliente existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente registrado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = ClienteDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "cliente no encontrado")
        })
    public ResponseEntity<ClienteDTO.Response> crear(@Valid @RequestBody ClienteDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Cliente",description = "Actualiza cliente existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = ClienteDTO.Response.class))),
            @ApiResponse(responseCode = "404",description = "cliente no encontrado")
        })
    public ResponseEntity<ClienteDTO.Response> actualizar(@PathVariable int id, @Valid @RequestBody ClienteDTO.Request request) {
        return ResponseEntity.ok(clienteService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cliente",description = "Elimina cliente por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "cliente no encontrado")
        })
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
