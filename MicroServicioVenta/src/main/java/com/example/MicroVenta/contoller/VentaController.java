package com.example.MicroVenta.contoller;

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

import com.example.MicroVenta.dto.VentaDTO;
import com.example.MicroVenta.model.Tienda;
import com.example.MicroVenta.model.Venta;
import com.example.MicroVenta.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@Tag(name = "Venta")
@RequestMapping("/api/v2/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
        @Operation(summary = "Obtener Ventas",description = "Obtener lista de ventas")
    public List<VentaDTO.Response> listarVentas(){
        return ventaService.listarTodos();
    }

     // agregar
     @PostMapping
        @Operation(summary = "Registrar Venta",description = "Registra venta existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
     public VentaDTO.Response agregarVenta(@Valid @RequestBody VentaDTO.Request request){
            return ventaService.crear(request);
     }
    // buscar
    @GetMapping("/{id_venta}")
        @Operation(summary = "Buscar Venta",description = "Busca venta por ID")
    public VentaDTO.Response buscarVenta(@PathVariable int id_venta){
        return ventaService.buscarPorId(id_venta);
    }
    // actualizar
    @PutMapping("/{id_venta}")
        @Operation(summary = "Actualizar Venta",description = "Actualiza venta existente")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "venta actualizada exitosamente",
                    content = @Content(mediaType = "application/JSON",
                        schema = @Schema(implementation = Venta.class))),
                @ApiResponse(responseCode = "404",description = "venta no encontrada")
            })
    public VentaDTO.Response actualizarVenta(@PathVariable int id_venta, @Valid @RequestBody VentaDTO.Request request){
        return ventaService.actualizar(id_venta, request);
    }
    // eliminar
    @DeleteMapping("/{id_venta}")
        @Operation(summary = "Eliminar Venta",description = "Elimina venta por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "venta eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "venta no encontrada")
        })
    public String eliminarVenta(@PathVariable int id_venta){
        try {
            ventaService.eliminar(id_venta);
            return "Venta eliminada correctamente";
        } catch (RuntimeException e) {
            return "Error al eliminar la venta";
        }
     }

}
