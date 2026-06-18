package com.example.MicroUsuarioSeguridad.service.impl;


import com.example.MicroUsuarioSeguridad.dto.GeneroDTO;
import com.example.MicroUsuarioSeguridad.model.Genero;
import com.example.MicroUsuarioSeguridad.repository.GeneroRepository;
import com.example.MicroUsuarioSeguridad.service.GeneroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    @Override
    public List<GeneroDTO.Response> listarGeneros() {
        return generoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GeneroDTO.Response obtenerGeneroPorId(Integer id_genero) {
        Genero genero = generoRepository.findById(id_genero)
                .orElseThrow(() -> new RuntimeException("Género no encontrado con ID: " + id_genero));
        return mapToResponse(genero);
    }

    @Override
    public GeneroDTO.Response crearGenero(GeneroDTO.Request request) {
        Genero genero = new Genero();
        genero.setNombre_genero(request.getNombre_genero());
        return mapToResponse(generoRepository.save(genero));
    }

    @Override
    public GeneroDTO.Response actualizarGenero(Integer id_genero, GeneroDTO.Request request) {
        Genero genero = generoRepository.findById(id_genero)
                .orElseThrow(() -> new RuntimeException("Género no encontrado con ID: " + id_genero));
        genero.setNombre_genero(request.getNombre_genero());
        return mapToResponse(generoRepository.save(genero));
    }

    @Override
    public void eliminarGenero(Integer id_genero) {
        if (!generoRepository.existsById(id_genero)) {
            throw new RuntimeException("Género no encontrado con ID: " + id_genero);
        }
        generoRepository.deleteById(id_genero);
    }

    // ── Mapper ──────────────────────────────────────────────────────────────
    private GeneroDTO.Response mapToResponse(Genero genero) {
        return new GeneroDTO.Response(
                genero.getId_genero(),
                genero.getNombre_genero()
        );
    }
}