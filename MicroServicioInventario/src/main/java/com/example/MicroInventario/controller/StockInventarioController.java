package com.example.MicroInventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroInventario.dto.StockInventarioDTO;
import com.example.MicroInventario.service.StockInventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/stock")
@Tag(name = "Stock Inventario", description = "Operaciones relacionadas con stock")
@RequiredArgsConstructor
public class StockInventarioController {

    @Autowired
    private StockInventarioService stockInventarioService;

    // LISTAR
    @GetMapping
    @Operation(summary = "Obtener Stock", description = "Obtiene lista de stock")
    public List<StockInventarioDTO.Response> listarStock() {

        return stockInventarioService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("{id}")
    @Operation(summary = "Buscar Stock", description = "Buscar stock por id")
    public StockInventarioDTO.Response buscarStock(
            @PathVariable int id) {

        return stockInventarioService.buscarPorId(id);
    }

    // AGREGAR
    @PostMapping
    @Operation(summary = "Registrar Stock", description = "Registrar nuevo stock")
    public StockInventarioDTO.Response agregarStock(
            @RequestBody StockInventarioDTO.Request request) {

        return stockInventarioService.guardar(request);
    }

    // ACTUALIZAR
    @PutMapping("{id}")
    @Operation(summary = "Actualizar Stock", description = "Actualizar stock existente")
    public StockInventarioDTO.Response actualizarStock(
            @PathVariable int id,
            @RequestBody StockInventarioDTO.Request request) {

        return stockInventarioService.actualizar(id, request);
    }

    // ELIMINAR
    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Stock", description = "Eliminar stock por id")
    public String eliminarStock(@PathVariable int id) {

        stockInventarioService.eliminar(id);

        return "Stock eliminado correctamente";
    }
}