package com.example.MicroInventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroInventario.entity.Producto;
import com.example.MicroInventario.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
@RequiredArgsConstructor
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // LISTAR
    @GetMapping
    @Operation(summary = "Obtener Productos", description = "Obtiene lista de productos")
    public List<Producto> listarProductos() {

        return productoService.getProductos();
    }

    // BUSCAR POR ID
    @GetMapping("{id_producto}")
    @Operation(summary = "Buscar Producto", description = "Buscar producto por id")
    public Producto buscarProducto(@PathVariable int id_producto) {

        return productoService.getProducto(id_producto);
    }

    // AGREGAR
    @PostMapping
    @Operation(summary = "Registrar Producto", description = "Registrar nuevo producto")
    public Producto agregarProducto(@RequestBody Producto producto) {

        return productoService.saveProducto(producto);
    }

    // ACTUALIZAR
    @PutMapping("{id_producto}")
    @Operation(summary = "Actualizar Producto", description = "Actualizar producto existente")
    public int actualizarProducto(
            @PathVariable int id_producto,
            @RequestBody Producto producto) {

        producto.setId_producto(id_producto);

        return productoService.updateProducto(producto);
    }

    // ELIMINAR
    @DeleteMapping("{id_producto}")
    @Operation(summary = "Eliminar Producto", description = "Eliminar producto por id")
    public String eliminarProducto(@PathVariable int id_producto) {

        if (productoService.deleteProducto(id_producto) == 1) {
            return "Producto eliminado correctamente";
        }

        return "Error al eliminar producto";
    }
}