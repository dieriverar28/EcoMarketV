package com.example.MicroPedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroPedido.entity.CuponDescuento;
import com.example.MicroPedido.service.CuponDescuentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v2/cupones_descuento")
@Tag(name = "Cupones de Descuento", description = "API para la gestión de cupones de descuento")
public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    // LISTAR TODOS
    @GetMapping
    @Operation(
            summary = "Listar cupones",
            description = "Obtiene todos los cupones de descuento registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Listado obtenido correctamente")
    })
    public List<CuponDescuento> listarCuponDescuentos() {
        return cuponDescuentoService.getAllCupones();
    }

    // AGREGAR
    @PostMapping
    @Operation(
            summary = "Crear cupón",
            description = "Registra un nuevo cupón de descuento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cupón creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public CuponDescuento agregarCuponDescuento(
            @Valid @RequestBody CuponDescuento cuponDescuento) {

        return cuponDescuentoService.saveCuponDescuento(cuponDescuento);
    }

    // BUSCAR
    @GetMapping("{id_cupon}")
    @Operation(
            summary = "Buscar cupón por ID",
            description = "Obtiene un cupón de descuento según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cupón encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cupón no encontrado")
    })
    public CuponDescuento buscarCuponDescuento(@PathVariable int id_cupon) {
        return cuponDescuentoService.getCuponDescuentoById(id_cupon);
    }

    // ACTUALIZAR
    @PutMapping("{id_cupon}")
    @Operation(
            summary = "Actualizar cupón",
            description = "Actualiza un cupón de descuento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cupón actualizado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Cupón no encontrado"),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos")
    })
    public int actualizarCuponDescuento(
            @PathVariable int id_cupon,
            @Valid @RequestBody CuponDescuento cuponDescuento) {

        cuponDescuento.setId_cupon(id_cupon);
        return cuponDescuentoService.updateCuponDescuento(cuponDescuento);
    }

    // ELIMINAR
    @DeleteMapping("{id_cupon}")
    @Operation(
            summary = "Eliminar cupón",
            description = "Elimina un cupón de descuento según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cupón eliminado correctamente"),
            @ApiResponse(responseCode = "404",
                    description = "Cupón no encontrado")
    })
    public String eliminarCuponDescuento(@PathVariable int id_cupon) {

        if (cuponDescuentoService.deleteCuponDescuento(id_cupon) == 1) {
            return "Cupon de descuento eliminado correctamente";
        }

        return "Error al eliminar el cupon de descuento";
    }
}