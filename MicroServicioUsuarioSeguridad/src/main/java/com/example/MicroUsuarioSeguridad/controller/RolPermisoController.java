package com.example.MicroUsuarioSeguridad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.service.RolPermisoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v2/roles_permisos")
@Tag(name = "Rol-Permiso Controller", description = "Endpoints para gestionar las relaciones entre roles y permisos")
public class RolPermisoController {

    @Autowired
    private RolPermisoService rolPermisoService;

    @GetMapping
    @Operation(summary = "Listar todas las relaciones Rol-Permiso", description = "Devuelve la lista completa de asignaciones")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public ResponseEntity<List<RolPermiso>> listarRolesPermisos(){
        return ResponseEntity.ok(rolPermisoService.listar());
    }

    @PostMapping
    @Operation(summary = "Asignar un permiso a un rol", description = "Crea una nueva asociación entre un rol y un permiso")
    @ApiResponse(responseCode = "201", description = "Asociación creada exitosamente")
    public ResponseEntity<RolPermiso> agregarRolPermiso(@Valid @RequestBody RolPermiso rolPermiso){
        return new ResponseEntity<RolPermiso>(rolPermisoService.guardar(rolPermiso), HttpStatus.CREATED);
    }

    @GetMapping("/{id_rol_permiso}")
    @Operation(summary = "Buscar Rol-Permiso por ID", description = "Obtiene los detalles de una asignación específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Asignación encontrada"),
        @ApiResponse(responseCode = "404", description = "No se encontró el registro", content = @Content)
    })
    public ResponseEntity<RolPermiso> buscarRolPermiso(
            @Parameter(description = "ID de la relación Rol-Permiso", example = "1")
            @PathVariable Integer id_rol_permiso){
        return ResponseEntity.ok(rolPermisoService.buscarPorId(id_rol_permiso));
    }

    @PutMapping("/{id_rol_permiso}")
    @Operation(summary = "Actualizar una relación Rol-Permiso", description = "Modifica los datos de una asignación existente")
    @ApiResponse(responseCode = "200", description = "Registro actualizado correctamente")
    public ResponseEntity<RolPermiso> actualizarRolPermiso(
            @Parameter(description = "ID de la relación a actualizar", example = "1")
            @PathVariable Integer id_rol_permiso, 
            @Valid @RequestBody RolPermiso rolPermiso){
        return ResponseEntity.ok(rolPermisoService.actualizar(id_rol_permiso, rolPermiso));
    }

    @DeleteMapping("/{id_rol_permiso}")
    @Operation(summary = "Eliminar una relación Rol-Permiso", description = "Elimina la asignación de un permiso a un rol")
    @ApiResponse(responseCode = "200", description = "Rol de permiso eliminado correctamente")
    public ResponseEntity<String> eliminarRolPermiso(
            @Parameter(description = "ID de la relación a eliminar", example = "1")
            @PathVariable Integer id_rol_permiso){
        rolPermisoService.eliminar(id_rol_permiso);
        return ResponseEntity.ok("Rol de permiso eliminado correctamente");
    }
}