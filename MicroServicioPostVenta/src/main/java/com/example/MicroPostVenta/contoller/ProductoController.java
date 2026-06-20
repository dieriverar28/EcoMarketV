package com.example.MicroPostVenta.contoller;

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

import com.example.MicroPostVenta.dto.ClienteDTO;
import com.example.MicroPostVenta.entity.Producto;
import com.example.MicroPostVenta.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@SuppressWarnings("unused")
@RestController
@RequestMapping ("api/v1/productos")
@Tag(name = "Producto", description = "Operaciones relacionadas con productos")

public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Obtener lista de productos")

    public List<Producto> listarProductos(){
        return productoService.getProductos();
    }
    //agregar
    @PostMapping
    @Operation(summary = "Agregar Producto",description = "Agregar nuevo producto")
    public Producto agregarProducto(@Valid @RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }
    //buscar
    @GetMapping("/{id_producto}")
    @Operation(summary = "Obtener Producto",description = "Obtener producto por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto encontrado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404",description = "producto no encontrado")
        })
    public Producto buscarProducto(@PathVariable int id_producto){
        return productoService.getProducto(id_producto);
    }
    //actualizar
    @PutMapping("/{id_producto}")
     @Operation(summary = "Actualizar Producto",description = "Actualiza producto existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404",description = "producto no encontrado")
        })
    public Producto actualizarProducto(@PathVariable int id_producto, @Valid @RequestBody Producto producto){
        return productoService.updateProducto(id_producto, producto);
    }
    //eliminar
    @DeleteMapping("/{id_producto}")
    @Operation(summary = "Eliminar Producto",description = "Elimina producto por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "producto no encontrado")
        })
    public String eliminarProducto(@PathVariable int id_producto){
        if (productoService.deleteProducto(id_producto)== 1) {
            return "Producto eliminado correctamente";
        }
        return "Error al eliminar el producto";
    }

}

