package com.example.MicroProducto.controller;

import com.example.MicroProducto.dto.ProductoDTO;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
        @Operation(summary = "Obtener todos los productos",description = "Obtener lista de todos los productos")

    public ResponseEntity<List<ProductoDTO.Response>> listarTodos() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/activos")
        @Operation(summary = "Obtener Productos Activos",description = "Obtener Lista Producto Activo")

    public ResponseEntity<List<ProductoDTO.Response>> listarActivos() {
        return ResponseEntity.ok(productoService.listarActivos());
    }

    @GetMapping("/categoria/{categoria}")
        @Operation(summary = "Obtener Productos Categoria",description = "Obtener Lista Por Categoria")

    public ResponseEntity<List<ProductoDTO.Response>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.listarPorCategoria(categoria));
    }

    @GetMapping("/{id}")
        @Operation(summary = "Obtener Productos Por ID",description = "Obtener Lista Producto ID")

    public ResponseEntity<ProductoDTO.Response> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar Producto",description = "Registra producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "producto registrado exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "producto no encontrado")
    })
    public ResponseEntity<ProductoDTO.Response> crear(@Valid @RequestBody ProductoDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Producto",description = "Actualiza producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "producto actualizado exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "producto no encontrado")
    })
    public ResponseEntity<ProductoDTO.Response> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO.Request request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar productos",description = "Elimina producto por id")
    @ApiResponses(value  = {
        @ApiResponse(responseCode = "200", description = "producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404",description = "Producto no encontrado")
    })
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
