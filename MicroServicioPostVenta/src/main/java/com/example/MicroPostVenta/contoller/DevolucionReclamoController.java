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
import com.example.MicroPostVenta.entity.DevolucionReclamo;
import com.example.MicroPostVenta.service.DevolucionReclamoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1/devolucion_reclamos")
@Tag(name = "DevolutionReclamo", description = "Operaciones relacionadas con devoluciones y reclamos")
public class DevolucionReclamoController {

    @Autowired
    private DevolucionReclamoService devolucionReclamoService;

    @GetMapping
    @Operation(summary = "Obtener Devoluciones y Reclamos",description = "Obtener lista de devoluciones y reclamos")

    public List<DevolucionReclamo> listarDevolucionReclamos(){
        return devolucionReclamoService.getDevolucionReclamos();
    }
    //agregar
    @PostMapping
    @Operation(summary = "Registrar Devolucion o Reclamo",description = "Registra devolucion o reclamo existente")

    public DevolucionReclamo agregDevolucionReclamo(@Valid @RequestBody DevolucionReclamo devolucionReclamo){
        return devolucionReclamoService.saveDevolucionReclamo(devolucionReclamo);
    }
    //buscar
    @GetMapping("{id_devolucion_reclamo}")
    @Operation(summary = "Obtener Devolucion o Reclamo",description = "Obtiene devolucion o reclamo por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "devolucion o reclamo encontrado",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = DevolucionReclamo.class))),
            @ApiResponse(responseCode = "404",description = "devolucion o reclamo no encontrado")
        })
    public DevolucionReclamo busDevolucionReclamo(@PathVariable int id_devolucion_reclamo){
        return devolucionReclamoService.getDevolucionReclamoById(id_devolucion_reclamo);
    }
    //actualizar
    @PutMapping("{id_devolucion_reclamo}")
    @Operation(summary = "Actualizar Devolucion o Reclamo",description = "Actualiza devolucion o reclamo existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "devolucion o reclamo actualizado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = DevolucionReclamo.class))),
            @ApiResponse(responseCode = "404",description = "devolucion o reclamo no encontrado")
        })
    public int actualizarDevolucionReclamo(@PathVariable int id_devolucion_reclamo, @Valid @RequestBody DevolucionReclamo devolucionReclamo){
        return devolucionReclamoService.updateDevolucionReclamo( devolucionReclamo);
    }
    //eliminar
    @DeleteMapping("{id_devolucion_reclamo}")
    @Operation(summary = "Eliminar Devolucion o Reclamo",description = "Elimina devolucion o reclamo por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "devolucion o reclamo eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "devolucion o reclamo no encontrado")
        })
    public String eliminarDevulucionReclamo(@PathVariable int id_devolucion_reclamo){
        if(devolucionReclamoService.deleteDevolucionReclamo(id_devolucion_reclamo)==1){
            return "Devolucion reclamo eliminado correctamente";
        }
        return "Error al eliminar el devolucion reclamo";
    }

}

