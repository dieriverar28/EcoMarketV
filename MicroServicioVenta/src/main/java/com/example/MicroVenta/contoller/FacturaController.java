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

import com.example.MicroVenta.entity.CuponDescuento;
import com.example.MicroVenta.entity.Factura;
import com.example.MicroVenta.service.FacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@Tag(name = "Factura")
@RequestMapping("api/v1/facturas")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @GetMapping
        @Operation(summary = "Obtener Facturas",description = "Obtener lista de facturas")

    public List<Factura> listarFacturas(){
        return facturaService.getFacturas();
    }

    //agregar
    @PostMapping
        @Operation(summary = "Registrar Factura",description = "Registra factura existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "factura registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Factura.class))),
            @ApiResponse(responseCode = "404",description = "factura no encontrada")
        })
    public int agregarFactura(@Valid @RequestBody Factura factura){
        return facturaService.saveFactura(factura);
     }
    //buscar
     @GetMapping("/{id_factura}")
        @Operation(summary = "Buscar Factura",description = "Busca factura por ID")
     public Factura buscarFactura(@PathVariable int id_factura){
        return  facturaService.getFactura(id_factura);
     }
    //actualizar
    @PutMapping("/{id_factura}")
        @Operation(summary = "Actualizar Factura",description = "Actualiza factura existente")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "factura actualizada exitosamente",
                    content = @Content(mediaType = "application/JSON",
                        schema = @Schema(implementation = Factura.class))),
                @ApiResponse(responseCode = "404",description = "factura no encontrada")
            })
    public int actualizarFactura(@PathVariable int id_factura, @Valid @RequestBody Factura factura){
        return facturaService.saveFactura(factura);
     }
    //eliminar
    @DeleteMapping("/{id_factura}")
        @Operation(summary = "Eliminar Factura",description = "Elimina factura por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "factura eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "factura no encontrada")
        })
    public String eliminarFactura(@PathVariable int id_factura){
        if ((facturaService).deleteFactura(id_factura)== 1) {
            return "Factura eliminada correctamente";
        }
        return "Error al eliminar la factura";
     }

}

