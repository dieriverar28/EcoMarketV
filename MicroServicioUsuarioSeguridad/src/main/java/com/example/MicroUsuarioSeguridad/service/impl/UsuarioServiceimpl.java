package com.example.MicroUsuarioSeguridad.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;
import com.example.MicroUsuarioSeguridad.entity.Genero;
import com.example.MicroUsuarioSeguridad.entity.RolPermiso;
import com.example.MicroUsuarioSeguridad.entity.Usuario;
import com.example.MicroUsuarioSeguridad.repository.GeneroRepository;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;
import com.example.MicroUsuarioSeguridad.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceimpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GeneroRepository generoRepository;
    private final RolPermisoRepository rolPermisoRepository;

    @Override
    public List<UsuarioDTO.Response> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO.Response buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return toResponse(usuario);
    }

    @Override
    public UsuarioDTO.Response guardar(UsuarioDTO.Request request) {
        Usuario usuario = new Usuario();
        aplicarDatos(usuario, request);
        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO.Response actualizar(Integer id, UsuarioDTO.Request request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        aplicarDatos(usuario, request);
        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    private void aplicarDatos(Usuario usuario, UsuarioDTO.Request request) {
        Genero genero = generoRepository.findById(request.getId_genero())
                .orElseThrow(() -> new RuntimeException("Genero no encontrado"));

        RolPermiso rol = rolPermisoRepository.findById(request.getId_rol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.setGenero(genero);
        usuario.setRol(rol);
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setIdTienda(request.getId_tienda());
        usuario.setEstado(Boolean.TRUE.equals(request.getEstado()));
    }

    private UsuarioDTO.Response toResponse(Usuario usuario) {
        return new UsuarioDTO.Response(
                usuario.getId_usuario(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getTelefono(),
                usuario.getGenero() != null ? usuario.getGenero().getId_genero() : null,
                usuario.getRol() != null ? usuario.getRol().getId_rol() : null,
                usuario.getIdTienda(),
                usuario.isEstado()
        );
    }
}
