package com.example.MicroDescuento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroDescuento.dto.CuponDescuentoDTO;
import com.example.MicroDescuento.entity.CuponDescuento;
import com.example.MicroDescuento.service.CuponDescuentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/cupones_descuento")
@Tag(name = "Cupones de Descuento", description = "Operaciones relacionadas con cupones de descuento")
@RequiredArgsConstructor
public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @GetMapping
    @Operation(summary = "Obtener Cupones de Descuento", description = "Obtener lista de cupones de descuento")
    public List<CuponDescuentoDTO.Response> listarCuponDescuentos() {
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
    public CuponDescuentoDTO.Response agregarCuponDescuento(
            @Valid @RequestBody CuponDescuento cuponDescuento) {

        return cuponDescuentoService.saveCuponDescuento(cuponDescuento);
    }

    // buscar
    @GetMapping("{id_cupon_descuento}")
    @Operation(summary = "Obtener Cupon de Descuento Por ID",description = "Obtener Lista Cupon de Descuento ID")
    public CuponDescuentoDTO.Response buscarCuponDescuento(
            @PathVariable int id_cupon_descuento) {

        return cuponDescuentoService.getCuponDescuentoById(id_cupon_descuento);
    }

    // actualizar
    @PutMapping("{id_cupon_descuento}")
    @Operation(summary = "Actualizar Cupon de Descuento",description = "Actualizar cupon de descuento existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cupon de descuento actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = CuponDescuento.class))),
            @ApiResponse(responseCode = "404",description = "cupon de descuento no encontrado")
        })
    public int actualizarCuponDescuento(
            @PathVariable int id_cupon_descuento,
            @Valid @RequestBody CuponDescuento cuponDescuento) {

        return cuponDescuentoService.updateCuponDescuento(cuponDescuento);
    }

    // eliminar
    @DeleteMapping("{id_cupon_descuento}")
    @Operation(summary = "Eliminar Cupon de Descuento",description = "Elimina cupon de descuento por id")
    @ApiResponses(value  = {
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

