package com.example.MicroVenta.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroVenta.dto.ClienteDTO;
import com.example.MicroVenta.entity.Boleta;
import com.example.MicroVenta.service.BoletaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SuppressWarnings("unused")
@Tag(name = "Boleta")
@RestController
@RequestMapping("api/v1/boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
        @Operation(summary = "Obtener Boletas",description = "Obtener lista de boletas")

    public List<Boleta> listarBoleta(){
        return boletaService.getBoletas();
    } 

    @PostMapping
    @Operation(summary = "Registrar Boleta",description = "Registra boleta existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "boleta registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Boleta.class))),
            @ApiResponse(responseCode = "404",description = "boleta no encontrada")
        })
    public Boleta agregarBoleta(@Valid @RequestBody Boleta boleta) {
        return boletaService.saveBoletas(boleta);
    }

    @GetMapping("{id_boleta}")
        @Operation(summary = "Buscar Boleta",description = "Busca boleta por ID")
    public Boleta buscarBoleta(@PathVariable int id_boleta) {
        return boletaService.getBoletaById(id_boleta);
    }
    
    @PutMapping("{id_boleta}")
     @Operation(summary = "Actualizar Boleta",description = "Actualiza boleta existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "boleta actualizada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Boleta.class))),
            @ApiResponse(responseCode = "404",description = "boleta no encontrada")
        })
    public int actualizarBoleta(@PathVariable int id_boleta, @Valid @RequestBody int boleta) {
        return boletaService.deleteBoleta(boleta);
    }
    
    @DeleteMapping("{id_boleta}")
        @Operation(summary = "Eliminar Boleta",description = "Elimina boleta por ID")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "boleta eliminada exitosamente"),
                @ApiResponse(responseCode = "404",description = "boleta no encontrada")
            })
    public String eliminarBoleta(@PathVariable int id_boleta) {
        if (boletaService.deleteBoleta(id_boleta)==1) {
            return "boleta eliminada";
        } 
        return "error al eliminar boleta";
    }

}

