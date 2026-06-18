package com.example.MicroUsuarioSeguridad.service;

import java.util.List;
import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;

public interface UsuarioService {
    List<UsuarioDTO.Response> listar();
    
    UsuarioDTO.Response buscarPorId(Integer id);
    
    // Recibe el DTO plano y devuelve el Response interno
    UsuarioDTO.Response guardar(UsuarioDTO request); 
    
    // Recibe el DTO plano y devuelve el Response interno
    UsuarioDTO.Response actualizar(Integer id, UsuarioDTO request); 
    
    void eliminar(Integer id);
}