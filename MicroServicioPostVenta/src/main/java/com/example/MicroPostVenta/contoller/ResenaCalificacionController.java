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
import com.example.MicroPostVenta.model.ResenaCalificacion;
import com.example.MicroPostVenta.service.ResenaCalificacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v2/resenas_calificaciones")
public class ResenaCalificacionController {
    @Autowired
    private ResenaCalificacionService resenaCalificacionService;

    @GetMapping
    @Operation(summary = "Obtener Reseñas de Calificación",description = "Obtener lista de reseñas de calificación")

    public List<ResenaCalificacion> listarResenasCalificaciones(){
        return resenaCalificacionService.getResenaCalificaciones();
    }
    //agregar
    @PostMapping
    @Operation(summary = "Agregar Reseña de Calificación",description = "Agregar nueva reseña de calificación")
    public ResenaCalificacion agregarResenaCalificacion(@Valid @RequestBody ResenaCalificacion resenaCalificacion){
        return resenaCalificacionService.saveResenaCalificacion(resenaCalificacion);
    }

    //buscar
    @GetMapping("/{id_resena_calificacion}")
    @Operation(summary = "Obtener Reseña de Calificación",description = "Obtiene reseña de calificación por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reseña de calificación encontrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = ResenaCalificacion.class))),
            @ApiResponse(responseCode = "404",description = "reseña de calificación no encontrada")
        })
    public ResenaCalificacion buscarResenaCalificacion(@PathVariable("id_resena_calificacion") int id_resena_calificacion){
        return resenaCalificacionService.getResenaCalificacion(id_resena_calificacion);
    }
    //actualizar
    @PutMapping("/{id_resena_calificacion}")
     @Operation(summary = "Actualizar Reseña de Calificación",description = "Actualiza reseña de calificación existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reseña de calificación actualizada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = ResenaCalificacion.class))),
            @ApiResponse(responseCode = "404",description = "reseña de calificación no encontrada")
        })
    public ResenaCalificacion actualizarResenaCalificacion(@PathVariable int id_resena_calificacion, @Valid @RequestBody ResenaCalificacion resenaCalificacion){
        return resenaCalificacionService.updateResenaCalificacion(id_resena_calificacion, resenaCalificacion);
    }
    //eliminar
    @DeleteMapping("/{id_resena_calificacion}")
    @Operation(summary = "Eliminar Reseña de Calificación",description = "Elimina reseña de calificación por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reseña de calificación eliminada exitosamente"),
            @ApiResponse(responseCode = "404",description = "reseña de calificación no encontrada")
        })
    public String eliminarResenaCalificacion(@PathVariable int id_resena_calificacion){
        if (resenaCalificacionService.deleteResenaCalificacion(id_resena_calificacion)== 1) {
            return "Reseña de calificación eliminada correctamente";
        }
        return "Error al eliminar la reseña de calificación";
    }

}
