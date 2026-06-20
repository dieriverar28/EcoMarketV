package com.example.MicroTiendaUbicacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.MicroTiendaUbicacion.dto.HorarioTiendaDTO;
import com.example.MicroTiendaUbicacion.service.HorarioTiendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/horarios_tienda")
@Tag(name = "Horarios de Tienda", description = "API para la gestión de horarios de las tiendas")
public class HorarioTiendaController {

    @Autowired
    private HorarioTiendaService horarioTiendaService;

    @Operation(summary = "Listar todos los horarios")
    @GetMapping
    public List<HorarioTiendaDTO.Response> listar() {
        return horarioTiendaService.listar();
    }

    @Operation(summary = "Buscar horario por ID")
    @GetMapping("/{id}")
    public HorarioTiendaDTO.Response buscarPorId(@PathVariable Integer id) {
        return horarioTiendaService.buscarPorId(id);
    }

    @Operation(summary = "Listar horarios de una tienda")
    @GetMapping("/tienda/{id_tienda}")
    public List<HorarioTiendaDTO.Response> buscarPorTienda(
            @PathVariable Integer id_tienda) {

        return horarioTiendaService.buscarPorTienda(id_tienda);
    }

    @Operation(summary = "Registrar un horario")
    @PostMapping
    public HorarioTiendaDTO.Response guardar(
            @Valid @RequestBody HorarioTiendaDTO.Request request) {

        return horarioTiendaService.guardar(request);
    }

    @Operation(summary = "Actualizar un horario")
    @PutMapping("/{id}")
    public HorarioTiendaDTO.Response actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody HorarioTiendaDTO.Request request) {

        return horarioTiendaService.actualizar(id, request);
    }

    @Operation(summary = "Eliminar un horario")
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {

        horarioTiendaService.eliminar(id);
        return "Horario eliminado correctamente";
    }
}