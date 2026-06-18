package com.example.MicroInventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroInventario.dto.TiendaDTO;
import com.example.MicroInventario.service.TiendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/tiendas")
@Tag(name = "Tiendas", description = "Operaciones relacionadas con tiendas")
@RequiredArgsConstructor
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // LISTAR
    @GetMapping
    @Operation(summary = "Obtener Tiendas", description = "Obtiene lista de tiendas")
    public List<TiendaDTO.Response> listarTiendas() {

        return tiendaService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("{id}")
    @Operation(summary = "Buscar Tienda", description = "Buscar tienda por id")
    public TiendaDTO.Response buscarTienda(@PathVariable int id) {

        return tiendaService.buscarPorId(id);
    }

    // AGREGAR
    @PostMapping
    @Operation(summary = "Registrar Tienda", description = "Registrar nueva tienda")
    public TiendaDTO.Response agregarTienda(
            @RequestBody TiendaDTO.Request request) {

        return tiendaService.guardar(request);
    }

    // ACTUALIZAR
    @PutMapping("{id}")
    @Operation(summary = "Actualizar Tienda", description = "Actualizar tienda existente")
    public TiendaDTO.Response actualizarTienda(
            @PathVariable int id,
            @RequestBody TiendaDTO.Request request) {

        return tiendaService.actualizar(id, request);
    }

    // ELIMINAR
    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Tienda", description = "Eliminar tienda por id")
    public String eliminarTienda(@PathVariable int id) {

        tiendaService.eliminar(id);

        return "Tienda eliminada correctamente";
    }
}