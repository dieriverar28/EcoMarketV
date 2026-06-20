package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroPedido.dto.TiendaDTO;
import com.example.MicroPedido.service.TiendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/tiendas")
@Tag(name = "Tiendas", description = "API para la gestión de tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // LISTAR TODAS
    @GetMapping
    @Operation(
            summary = "Listar tiendas",
            description = "Obtiene todas las tiendas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public List<TiendaDTO.Response> listarTiendas() {
        return tiendaService.listarTiendas();
    }

    // BUSCAR POR ID
    @GetMapping("{id_tienda}")
    @Operation(
            summary = "Buscar tienda por ID",
            description = "Obtiene una tienda según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tienda encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TiendaDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Tienda no encontrada")
    })
    public TiendaDTO.Response buscarTienda(@PathVariable int id_tienda) {
        return tiendaService.buscarTienda(id_tienda);
    }

    // CREAR
    @PostMapping
    @Operation(
            summary = "Crear tienda",
            description = "Registra una nueva tienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Tienda creada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TiendaDTO.Response.class))),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public TiendaDTO.Response guardarTienda(
            @Valid @RequestBody TiendaDTO.Request request) {

        return tiendaService.guardarTienda(request);
    }

    // ACTUALIZAR
    @PutMapping("{id_tienda}")
    @Operation(
            summary = "Actualizar tienda",
            description = "Actualiza una tienda existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tienda actualizada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TiendaDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Tienda no encontrada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public TiendaDTO.Response actualizarTienda(
            @PathVariable int id_tienda,
            @Valid @RequestBody TiendaDTO.Request request) {

        return tiendaService.actualizarTienda(id_tienda, request);
    }

    // ELIMINAR
    @DeleteMapping("{id_tienda}")
    @Operation(
            summary = "Eliminar tienda",
            description = "Elimina una tienda según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Tienda eliminada correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Tienda no encontrada")
    })
    public String eliminarTienda(@PathVariable int id_tienda) {

        if (tiendaService.eliminarTienda(id_tienda) == 1) {
            return "Tienda eliminada correctamente";
        }

        return "Error al eliminar tienda";
    }
}