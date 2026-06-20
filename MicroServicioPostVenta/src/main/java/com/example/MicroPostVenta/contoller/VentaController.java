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
import com.example.MicroPostVenta.entity.Venta;
import com.example.MicroPostVenta.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1/ventas")
@Tag(name = "Venta", description = "Operaciones relacionadas con ventas")

public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    @Operation(summary = "Obtener Ventas",description = "Obtener lista de ventas")
    public List<Venta> listarVentas(){
        return ventaService.getVentas();
    }

     //agregar
     @PostMapping
    @Operation(summary = "Agregar Venta",description = "Agregar nueva venta")
        public Venta agregarVenta(@Valid @RequestBody Venta venta){
            return ventaService.saveVenta(venta);
        }
    //buscar
    @GetMapping("/{id_venta}")
     @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public Venta buscarVenta(@PathVariable int id_venta){
        return ventaService.getVenta(id_venta);
    }
    //actualizar
    @PutMapping("/{id_venta}")
    @Operation(summary = "Actualizar Venta",description = "Actualiza venta existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta actualizada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public int actualizarVenta(@PathVariable int id_venta, @Valid @RequestBody Venta venta){
        return ventaService.updateVenta(venta);
    }
    //eliminar
    @DeleteMapping("/{id_venta}")
    @Operation(summary = "Eliminar Venta",description = "Elimina venta por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public String eliminarVenta(@PathVariable int id_venta){
        if (ventaService.deleteVenta(id_venta)== 1) {
            return "Venta eliminada correctamente";
        }
        return "Error al eliminar la venta";
    }

}

