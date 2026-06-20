package com.example.MicroPostVenta.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroPostVenta.entity.CuponDescuento;
import com.example.MicroPostVenta.service.CuponDescuentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/cupones_descuento")
@Tag(name = "CuponDescuento", description = "Operaciones relacionadas con cupones de descuento")


public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    @GetMapping
    public List<CuponDescuento> listarCuponDescuentos() {
        return cuponDescuentoService.obtenerCuponDescuentos();
    }

    // agregar
    @PostMapping
    @Operation(summary = "Agregar cupon de descuento",description = "Agregar nuevo cupon de descuento")

    public CuponDescuento agregarCuponDescuento(
        @Valid @RequestBody CuponDescuento cuponDescuento) {
        return cuponDescuentoService.crearCuponDescuento(cuponDescuento);
    }

    // buscar
    @GetMapping("{id_cupon_descuento}")
    @Operation(summary = "Obtener Cupon de Descuento",description = "Obtener cupon de descuento por ID")
    public CuponDescuento buscarCuponDescuento(
            @PathVariable int id_cupon_descuento) {

        return cuponDescuentoService.buscarCuponDescuento(id_cupon_descuento);
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
    public CuponDescuento actualizarCuponDescuento(@PathVariable("id_cupon_descuento") int id_cupon_descuento, @Valid @RequestBody CuponDescuento cuponDescuento) {
        cuponDescuento.setId_cupon_descuento(id_cupon_descuento);
        return cuponDescuentoService.actualizarCuponDescuento(cuponDescuento);
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

        if (cuponDescuentoService.eliminarCuponDescuento(id_cupon_descuento) == 1) {
            return "Cupon de descuento eliminado correctamente";
        }

        return "Error al eliminar el cupon de descuento";
    }

}

