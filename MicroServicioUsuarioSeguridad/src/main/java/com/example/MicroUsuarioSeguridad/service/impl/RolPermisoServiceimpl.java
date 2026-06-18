package com.example.MicroUsuarioSeguridad.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.MicroUsuarioSeguridad.model.RolPermiso;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository; // Ajusta según tu repo
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
        
        // Aquí seteas los campos que necesites actualizar, por ejemplo:
        // existente.setRol(rolPermiso.getRol());
        // existente.setPermiso(rolPermiso.getPermiso());
        
        return rolPermisoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        rolPermisoRepository.deleteById(id);
    }
}