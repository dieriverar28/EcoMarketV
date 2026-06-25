package com.example.MicroProducto.controller;

import com.example.MicroProducto.assembler.ProductoModelAssembler;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los productos",description = "Obtener lista de todos los productos")
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO.Response>>> listarTodos() {
        List<EntityModel<ProductoDTO.Response>> productos = productoService.listarTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                CollectionModel.of(productos)
                        .add(linkTo(methodOn(ProductoController.class).listarTodos()).withSelfRel())
        );
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtener Productos Activos",description = "Obtener Lista Producto Activo")
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO.Response>>> listarActivos() {
        List<EntityModel<ProductoDTO.Response>> productos = productoService.listarActivos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                CollectionModel.of(productos)
                        .add(linkTo(methodOn(ProductoController.class).listarActivos()).withSelfRel())
                        .add(linkTo(methodOn(ProductoController.class).listarTodos()).withRel("todos-productos"))
        );
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtener Productos Categoria",description = "Obtener Lista Por Categoria")
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO.Response>>> listarPorCategoria(@PathVariable String categoria) {
        List<EntityModel<ProductoDTO.Response>> productos = productoService.listarPorCategoria(categoria)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(
                CollectionModel.of(productos)
                        .add(linkTo(methodOn(ProductoController.class).listarPorCategoria(categoria)).withSelfRel())
                        .add(linkTo(methodOn(ProductoController.class).listarTodos()).withRel("todos-productos"))
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Productos Por ID",description = "Obtener Lista Producto ID")
    public ResponseEntity<EntityModel<ProductoDTO.Response>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(productoService.buscarPorId(id)));
    }

    @PostMapping
    @Operation(summary = "Registrar Producto",description = "Registra producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "producto registrado exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "producto no encontrado")
    })
    public ResponseEntity<EntityModel<ProductoDTO.Response>> crear(@Valid @RequestBody ProductoDTO.Request request) {
        ProductoDTO.Response response = productoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(assembler.toModel(response)
                        .add(linkTo(methodOn(ProductoController.class).listarTodos()).withRel("lista-productos")));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Producto",description = "Actualiza producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "producto actualizado exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "producto no encontrado")
    })
    public ResponseEntity<EntityModel<ProductoDTO.Response>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO.Request request) {
        ProductoDTO.Response response = productoService.actualizar(id, request);
        return ResponseEntity.ok(assembler.toModel(response));
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