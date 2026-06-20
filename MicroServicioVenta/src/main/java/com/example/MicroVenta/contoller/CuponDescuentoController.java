package com.example.MicroVenta.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroVenta.entity.CuponDescuento;
import com.example.MicroVenta.service.CuponDescuentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "CuponDescuento")
@RequestMapping("api/v1/cupones_descuento")

public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @GetMapping
        @Operation(summary = "Obtener Cupones de Descuento",description = "Obtener lista de cupones de descuento")
    public List<CuponDescuento> listarCuponDescuentos() {
        return cuponDescuentoService.getAllCupones();
    }

    // agregar
    @PostMapping
        @Operation(summary = "Registrar Cupon de Descuento",description = "Registra cupon de descuento existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cupon de descuento registrado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "404",description = "cupon de descuento no encontrado")
        })
    public CuponDescuento agregarCuponDescuento(
            @Valid @RequestBody CuponDescuento cuponDescuento) {

        return cuponDescuentoService.saveCuponDescuento(cuponDescuento);
    }

    // buscar
    @GetMapping("{id_cupon_descuento}")
        @Operation(summary = "Buscar Cupon de Descuento",description = "Busca cupon de descuento por ID")
    public CuponDescuento buscarCuponDescuento(
            @PathVariable int id_cupon_descuento) {

        return cuponDescuentoService.getCuponDescuentoById(id_cupon_descuento);
    }

    // actualizar
    @PutMapping("{id_cupon_descuento}")
    @Operation(summary = "Actualizar Cupon de Descuento",description = "Actualiza cupon de descuento existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cupon de descuento actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "404",description = "cupon de descuento no encontrado")
        })
    public CuponDescuento actualizarCuponDescuento(
            @PathVariable int id_cupon_descuento,
            @Valid @RequestBody CuponDescuento cuponDescuento) {

        return cuponDescuentoService.saveCuponDescuento(cuponDescuento);
    }

    // eliminar
    @DeleteMapping("{id_cupon_descuento}")
    @Operation(summary = "Eliminar Cupon de Descuento",description = "Elimina cupon de descuento por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cupon de descuento eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "cupon de descuento no encontrado")
        })
    public String eliminarCuponDescuento(
            @PathVariable int id_cupon_descuento) {

        if (cuponDescuentoService.deleteCuponDescuento(id_cupon_descuento) == 1) {
            return "Cupon de descuento eliminado correctamente";
        }

        return "Error al eliminar el cupon de descuento";
    }

}

