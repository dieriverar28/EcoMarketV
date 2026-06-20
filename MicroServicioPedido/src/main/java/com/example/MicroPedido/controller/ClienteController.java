package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroPedido.dto.ClienteDTO;
import com.example.MicroPedido.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/clientes")
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(
            summary = "Listar clientes",
            description = "Obtiene todos los clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public ResponseEntity<List<ClienteDTO.Response>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PostMapping
    @Operation(
            summary = "Crear cliente",
            description = "Registra un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Cliente creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDTO.Response.class))),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public ResponseEntity<ClienteDTO.Response> crearCliente(
            @Valid @RequestBody ClienteDTO.Request request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.crearCliente(request));
    }

    @GetMapping("/{id_cliente}")
    @Operation(
            summary = "Buscar cliente por ID",
            description = "Obtiene un cliente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO.Response> buscarPorId(
            @PathVariable int id_cliente) {

        return ResponseEntity.ok(clienteService.buscarPorId(id_cliente));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(
            summary = "Buscar cliente por nombre",
            description = "Obtiene un cliente según su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO.Response> buscarPorNombre(
            @PathVariable String nombre) {

        return ResponseEntity.ok(clienteService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id_cliente}")
    @Operation(
            summary = "Actualizar cliente",
            description = "Actualiza la información de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cliente actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public ResponseEntity<ClienteDTO.Response> actualizarCliente(
            @PathVariable int id_cliente,
            @Valid @RequestBody ClienteDTO.Request request) {

        return ResponseEntity.ok(
                clienteService.actualizarCliente(id_cliente, request));
    }

    @DeleteMapping("/{id_cliente}")
    @Operation(
            summary = "Eliminar cliente",
            description = "Elimina un cliente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cliente eliminado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Cliente no encontrado")
    })
    public ResponseEntity<String> eliminarCliente(
            @PathVariable int id_cliente) {

        clienteService.eliminarCliente(id_cliente);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }
}