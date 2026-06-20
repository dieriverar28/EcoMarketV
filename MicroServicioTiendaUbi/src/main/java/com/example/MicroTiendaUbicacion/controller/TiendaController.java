package com.example.MicroTiendaUbicacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroTiendaUbicacion.dto.TiendaDTO;
import com.example.MicroTiendaUbicacion.service.TiendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tiendas")
@Tag(name = "Tiendas", description = "API para la gestión de tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @Operation(summary = "Listar todas las tiendas")
    @GetMapping
    public List<TiendaDTO.Response> listar() {
        return tiendaService.listar();
    }

    @Operation(summary = "Buscar una tienda por ID")
    @GetMapping("/{id}")
    public TiendaDTO.Response buscarPorId(@PathVariable Integer id) {
        return tiendaService.buscarPorId(id);
    }

    @Operation(summary = "Registrar una nueva tienda")
    @PostMapping
    public TiendaDTO.Response guardar(
            @Valid @RequestBody TiendaDTO.Request request) {

        return tiendaService.guardar(request);
    }

    @Operation(summary = "Actualizar una tienda existente")
    @PutMapping("/{id}")
    public TiendaDTO.Response actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TiendaDTO.Request request) {

        return tiendaService.actualizar(id, request);
    }

    @Operation(summary = "Eliminar una tienda")
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {

        tiendaService.eliminar(id);
        return "Tienda eliminada correctamente";
    }
}