package com.example.MicroProducto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("api/v1/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con categoria")
@RequiredArgsConstructor
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // LISTAR TODAS
    @GetMapping
        @Operation(summary = "Obtener Categoria",description = "Obtener lista de categoria")

    public List<Categoria> listarCategorias() {
        return categoriaService.getCategorias();
    }

    // AGREGAR
    @PostMapping
        @Operation(summary = "Registrar Categoria",description = "Registra categoria existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "categoria registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404",description = "categoria no encontrado")
        })
    public Categoria agregarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.saveCategoria(categoria);
    }

    // BUSCAR POR ID
    @GetMapping("{id_categoria}")
    @Operation(summary = "Obtener Categotria Por ID",description = "Obtener Lista Categoria ID")
    public Categoria buscarCategoria(@PathVariable int id_categoria) {
        return categoriaService.getCategoria(id_categoria);
    }

    // ACTUALIZAR
    @PutMapping("{id_categoria}")
    @Operation(summary = "Actualizar Categoria",description = "Actualiza categoria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "categoria actualizada exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "categoria no encontrado")
    })
    public int actualizarCategoria(@PathVariable int id_categoria,
            @RequestBody Categoria categoria) {

        categoria.setId_categoria(id_categoria);
        return categoriaService.updateCategoria(categoria);
    }

    // ELIMINAR
    @DeleteMapping("{id_categoria}")
    @Operation(summary = "Eliminar categoria",description = "Elimina categoria por id")
    @ApiResponses(value  = {
        @ApiResponse(responseCode = "200", description = "categoria eliminada exitosamente"),
        @ApiResponse(responseCode = "404",description = "categoria no encontrada")
    })
    public String eliminarCategoria(@PathVariable int id_categoria) {

        if (categoriaService.deleteCategoria(id_categoria) == 1) {
            return "Categoría eliminada correctamente";
        }

        return "Error al eliminar categoría";
    }
}