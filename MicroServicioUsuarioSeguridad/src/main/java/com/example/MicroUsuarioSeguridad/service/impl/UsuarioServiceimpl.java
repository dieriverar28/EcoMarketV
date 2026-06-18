package com.example.MicroUsuarioSeguridad.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;
import com.example.MicroUsuarioSeguridad.model.Usuario;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;
import com.example.MicroUsuarioSeguridad.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceimpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

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
    public UsuarioDTO.Response guardar(UsuarioDTO request) { // <-- Cambiado a UsuarioDTO
        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setIdTienda(request.getId_tienda());
        usuario.setEstado(request.getEstado());

        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO.Response actualizar(Integer id, UsuarioDTO request) { // <-- Cambiado a UsuarioDTO
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setTelefono(request.getTelefono());
        usuario.setIdTienda(request.getId_tienda());
        usuario.setEstado(request.getEstado());

        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
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