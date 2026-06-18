package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.ProveedorDTO;
import com.example.MicroEnvio.model.Proveedor;
import com.example.MicroEnvio.repository.ProveedorRepository;
import com.example.MicroEnvio.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProveedorDTO.Response> listarTodos() {
        return proveedorRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProveedorDTO.Response buscarPorId(int id) {
        Proveedor p = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
        return mapToResponse(p);
    }

    @Override
    @Transactional
    public ProveedorDTO.Response crear(ProveedorDTO.Request request) {
        if (proveedorRepository.existsByNombre(request.getNombre()))
            throw new RuntimeException("Ya existe un proveedor con el nombre: " + request.getNombre());
        Proveedor p = new Proveedor();
        p.setNombre(request.getNombre());
        return mapToResponse(proveedorRepository.save(p));
    }

    @Override
    @Transactional
    public ProveedorDTO.Response actualizar(int id, ProveedorDTO.Request request) {
        Proveedor p = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + id));
        p.setNombre(request.getNombre());
        return mapToResponse(proveedorRepository.save(p));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!proveedorRepository.existsById(id))
            throw new RuntimeException("Proveedor no encontrado con id: " + id);
        proveedorRepository.deleteById(id);
    }

    private ProveedorDTO.Response mapToResponse(Proveedor p) {
        return new ProveedorDTO.Response(p.getId(), p.getNombre());
    }
}
