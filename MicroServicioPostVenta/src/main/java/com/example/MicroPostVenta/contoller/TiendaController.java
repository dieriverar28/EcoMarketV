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
import com.example.MicroPostVenta.entity.Tienda;
import com.example.MicroPostVenta.service.TiendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping ("api/v1/tiendas")
@Tag(name = "Tienda", description = "Operaciones relacionadas con tiendas")

public class TiendaController {
    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    @Operation(summary = "Obtener Tiendas",description = "Obtener lista de tiendas")

    public List<Tienda> listarTiendas(){
        return tiendaService.getTiendas();
    }
    //agregar
    @PostMapping
    @Operation(summary = "Agregar Tienda",description = "Agregar nueva tienda")
    public Tienda agregarTienda(@Valid @RequestBody Tienda tienda){
        return tiendaService.saveTienda(tienda);
     }
    //buscar
    @GetMapping("/{id_tienda}")
    @Operation(summary = "Registrar Tienda",description = "Registra tienda existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "tienda registrada exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "404",description = "tienda no encontrada")
    })
    public Tienda buscarTienda(@PathVariable int id_tienda){
        return tiendaService.getTienda(id_tienda);
    }

    //actualizar
    @PutMapping("/{id_tienda}")
    @Operation(summary = "Actualizar Tienda",description = "Actualiza tienda existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tienda actualizada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Tienda.class))),
            @ApiResponse(responseCode = "404",description = "tienda no encontrada")
        })
        public Tienda actualizarTienda(@PathVariable int id_tienda, @Valid @RequestBody Tienda tienda){
        return tiendaService.updateTienda(id_tienda, tienda);
    }
    //eliminar
    @DeleteMapping("/{id_tienda}")
    @Operation(summary = "Eliminar Tienda",description = "Elimina tienda por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "tienda eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "tienda no encontrada")
        })
    public String eliminarTienda(@PathVariable int id_tienda){
        if (tiendaService.deleteTienda(id_tienda) == 1) {
            return "Tienda eliminada correctamente";
        }
        return "Error al eliminar la tienda";
    }

}

