package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroPedido.entity.Pedido;
import com.example.MicroPedido.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1/pedidos")
@Tag(name = "Pedidos", description = "API para la gestión de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Operation(
            summary = "Listar pedidos",
            description = "Obtiene todos los pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public List<Pedido> listarPedidos() {
        return pedidoService.getPedidos();
    }

    // agregar
    @PostMapping
    @Operation(
            summary = "Crear pedido",
            description = "Registra un nuevo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pedido creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public Pedido agregarPedido(@Valid @RequestBody Pedido pedido) {
        return pedidoService.savePedido(pedido);
    }

    // buscar
    @GetMapping("/{id_pedido}")
    @Operation(
            summary = "Buscar pedido por ID",
            description = "Obtiene un pedido según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pedido encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404",
                    description = "Pedido no encontrado")
    })
    public Pedido buscarPedido(@PathVariable int id_pedido) {
        return pedidoService.getPedido(id_pedido);
    }

    // actualizar
    @PutMapping("/{id_pedido}")
    @Operation(
            summary = "Actualizar pedido",
            description = "Actualiza un pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pedido actualizado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Pedido no encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public int actualizarPedido(
            @PathVariable int id_pedido,
            @Valid @RequestBody Pedido pedido) {

        return pedidoService.updatePedido(pedido);
    }

    // eliminar
    @DeleteMapping("/{id_pedido}")
    @Operation(
            summary = "Eliminar pedido",
            description = "Elimina un pedido según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Pedido eliminado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Pedido no encontrado")
    })
    public String eliminarPedido(@PathVariable int id_pedido) {

        if (pedidoService.deletePedido(id_pedido) == 1) {
            return "Pedido eliminado correctamente";
        }

        return "Error al eliminar el pedido";
    }
}