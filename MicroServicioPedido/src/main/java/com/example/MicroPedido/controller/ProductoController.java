package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroPedido.dto.ProductoDTO;
import com.example.MicroPedido.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/productos")
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // LISTAR TODOS
    @GetMapping
    @Operation(
            summary = "Listar productos",
            description = "Obtiene todos los productos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public List<ProductoDTO.Response> listarProductos() {
        return productoService.listarTodos();
    }

    // LISTAR ACTIVOS
    @GetMapping("/activos")
    @Operation(
            summary = "Listar productos activos",
            description = "Obtiene todos los productos activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public List<ProductoDTO.Response> listarActivos() {
        return productoService.listarActivos();
    }

    // BUSCAR POR ID
    @GetMapping("{id}")
    @Operation(
            summary = "Buscar producto por ID",
            description = "Obtiene un producto según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Producto encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Producto no encontrado")
    })
    public ProductoDTO.Response buscarProducto(@PathVariable int id) {
        return productoService.buscarPorId(id);
    }

    // CREAR
    @PostMapping
    @Operation(
            summary = "Crear producto",
            description = "Registra un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Producto creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDTO.Response.class))),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public ProductoDTO.Response crearProducto(
            @Valid @RequestBody ProductoDTO.Request request) {

        return productoService.crear(request);
    }

    // ACTUALIZAR
    @PutMapping("{id}")
    @Operation(
            summary = "Actualizar producto",
            description = "Actualiza un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Producto actualizado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoDTO.Response.class))),
            @ApiResponse(responseCode = "404",
                    description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public ProductoDTO.Response actualizarProducto(
            @PathVariable int id,
            @Valid @RequestBody ProductoDTO.Request request) {

        return productoService.actualizar(id, request);
    }

    // ELIMINAR
    @DeleteMapping("{id}")
    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Producto no encontrado")
    })
    public String eliminarProducto(@PathVariable int id) {

        if (productoService.eliminar(id) == 1) {
            return "Producto eliminado correctamente";
        }

        return "Error al eliminar producto";
    }
}