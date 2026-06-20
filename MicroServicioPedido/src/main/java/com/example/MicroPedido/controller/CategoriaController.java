package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroPedido.entity.Categoria;
import com.example.MicroPedido.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/categorias")
@Tag(name = "Categorías", description = "API para la gestión de categorías")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // LISTAR TODAS
    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene todas las categorías registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    public List<Categoria> listarCategorias() {
        return categoriaService.getCategorias();
    }

    // AGREGAR
    @PostMapping
    @Operation(summary = "Crear categoría", description = "Registra una nueva categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría creada correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public Categoria agregarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.saveCategoria(categoria);
    }

    // BUSCAR POR ID
    @GetMapping("{id_categoria}")
    @Operation(summary = "Buscar categoría por ID", description = "Obtiene una categoría según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public Categoria buscarCategoria(@PathVariable int id_categoria) {
        return categoriaService.getCategoria(id_categoria);
    }

    // ACTUALIZAR
    @PutMapping("{id_categoria}")
    @Operation(summary = "Actualizar categoría", description = "Actualiza una categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public int actualizarCategoria(@PathVariable int id_categoria,
            @RequestBody Categoria categoria) {

        categoria.setId_categoria(id_categoria);
        return categoriaService.updateCategoria(categoria);
    }

    // ELIMINAR
    @DeleteMapping("{id_categoria}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public String eliminarCategoria(@PathVariable int id_categoria) {

        if (categoriaService.deleteCategoria(id_categoria) == 1) {
            return "Categoría eliminada correctamente";
        }

        return "Error al eliminar categoría";
    }
}