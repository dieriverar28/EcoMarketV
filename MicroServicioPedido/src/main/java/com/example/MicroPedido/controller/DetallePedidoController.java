package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroPedido.entity.DetallePedido;
import com.example.MicroPedido.service.DetallePedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/detalle_pedidos")
@Tag(name = "Detalle Pedido", description = "API para la gestión de detalles de pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    // LISTAR TODOS
    @GetMapping
    @Operation(
            summary = "Listar detalles de pedido",
            description = "Obtiene todos los detalles de pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public List<DetallePedido> listarDetallePedidos() {
        return detallePedidoService.getDetallesPedido();
    }

    // AGREGAR
    @PostMapping
    @Operation(
            summary = "Crear detalle de pedido",
            description = "Registra un nuevo detalle de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Detalle creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetallePedido.class))),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public DetallePedido agregarDetallePedido(
            @Valid @RequestBody DetallePedido detallePedido) {

        return detallePedidoService.saveDetallePedido(detallePedido);
    }

    // BUSCAR
    @GetMapping("{id_detalle}")
    @Operation(
            summary = "Buscar detalle por ID",
            description = "Obtiene un detalle de pedido según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Detalle encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DetallePedido.class))),
            @ApiResponse(responseCode = "404",
                    description = "Detalle no encontrado")
    })
    public DetallePedido buscarDetallePedido(@PathVariable int id_detalle) {
        return detallePedidoService.getDetallePedidoById(id_detalle);
    }

    // ACTUALIZAR
    @PutMapping("{id_detalle}")
    @Operation(
            summary = "Actualizar detalle de pedido",
            description = "Actualiza un detalle de pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Detalle actualizado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Detalle no encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public int actualizarDetallePedido(
            @PathVariable int id_detalle,
            @Valid @RequestBody DetallePedido detallePedido) {

        detallePedido.setId_detalle(id_detalle);
        return detallePedidoService.updateDetallePedido(detallePedido);
    }

    // ELIMINAR
    @DeleteMapping("{id_detalle}")
    @Operation(
            summary = "Eliminar detalle de pedido",
            description = "Elimina un detalle de pedido según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Detalle eliminado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Detalle no encontrado")
    })
    public String eliminarDetallePedido(@PathVariable int id_detalle) {

        if (detallePedidoService.deleteDetallePedido(id_detalle) == 1) {
            return "Detalle pedido eliminado correctamente";
        }

        return "Error al eliminar el detalle del pedido";
    }
}