package com.example.MicroUsuarioSeguridad.service;

import java.util.List;
import com.example.MicroUsuarioSeguridad.model.RolPermiso;

public interface RolPermisoService {
    List<RolPermiso> listar();
    RolPermiso guardar(RolPermiso rolPermiso);
    RolPermiso buscarPorId(Integer id);
    RolPermiso actualizar(Integer id, RolPermiso rolPermiso);
    void eliminar(Integer id);
}