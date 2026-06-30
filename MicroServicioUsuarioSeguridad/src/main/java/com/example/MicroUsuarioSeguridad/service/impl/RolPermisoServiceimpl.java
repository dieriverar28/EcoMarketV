package com.example.MicroUsuarioSeguridad.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MicroUsuarioSeguridad.entity.RolPermiso;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.service.RolPermisoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolPermisoServiceimpl implements RolPermisoService {

    private final RolPermisoRepository rolPermisoRepository;

    @Override
    public List<RolPermiso> listar() {
        return rolPermisoRepository.findAll();
    }

    @Override
    public RolPermiso guardar(RolPermiso rolPermiso) {
        return rolPermisoRepository.save(rolPermiso);
    }

    @Override
    public RolPermiso buscarPorId(Integer id) {
        return rolPermisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol-Permiso no encontrado"));
    }

    @Override
    public RolPermiso actualizar(Integer id, RolPermiso rolPermiso) {
        RolPermiso existente = rolPermisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol-Permiso no encontrado"));

        existente.setId_rol(rolPermiso.getId_rol());
        existente.setId_permiso(rolPermiso.getId_permiso());
        existente.setNombre_rol(rolPermiso.getNombre_rol());
        existente.setModulo(rolPermiso.getModulo());
        existente.setAccion(rolPermiso.getAccion());

        return rolPermisoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        rolPermisoRepository.deleteById(id);
    }
}
