package com.example.MicroInventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroInventario.entity.Producto;
import com.example.MicroInventario.service.ProductoService;
import com.example.MicroInventario.client.ProductoClient;

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
    
    @Autowired
    private ProductoClient productoClient;

    // LISTAR PRODUCTOS DESDE MS-PRODUCTO VIA EUREKA
    @GetMapping("/desde-ms-producto")
    @Operation(summary = "Obtener Productos de MS-PRODUCTO", description = "Obtiene lista de productos desde el microservicio ms-producto vía Eureka")
    public List<Object> listarProductosDeServicio() {
        return productoClient.listarProductos();
    }

    // LISTAR PRODUCTOS ACTIVOS DESDE MS-PRODUCTO VIA EUREKA
    @GetMapping("/activos-ms-producto")
    @Operation(summary = "Obtener Productos Activos de MS-PRODUCTO", description = "Obtiene lista de productos activos desde ms-producto vía Eureka")
    public List<Object> listarProductosActivosDeServicio() {
        return productoClient.listarProductosActivos();
    }

    // LISTAR PRODUCTOS LOCALES
    @GetMapping
    @Operation(summary = "Obtener Productos", description = "Obtiene lista de productos locales")
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
