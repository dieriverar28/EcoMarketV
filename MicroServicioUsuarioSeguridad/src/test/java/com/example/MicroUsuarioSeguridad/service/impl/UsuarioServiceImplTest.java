package com.example.MicroUsuarioSeguridad.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroUsuarioSeguridad.dto.UsuarioDTO;
import com.example.MicroUsuarioSeguridad.entity.Genero;
import com.example.MicroUsuarioSeguridad.entity.RolPermiso;
import com.example.MicroUsuarioSeguridad.entity.Usuario;
import com.example.MicroUsuarioSeguridad.repository.GeneroRepository;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;
import com.example.MicroUsuarioSeguridad.repository.UsuarioRepository;

public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private GeneroRepository generoRepository;

    @Mock
    private RolPermisoRepository rolPermisoRepository;

    @InjectMocks
    private UsuarioServiceimpl usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarUsuarios() {
        Usuario usuario = crearUsuario();
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<UsuarioDTO.Response> resultado = usuarioService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(usuario.getNombre(), resultado.get(0).getNombre());
        assertEquals(usuario.getCorreo(), resultado.get(0).getCorreo());
    }

    @Test
    void buscarUsuarioPorId() {
        Usuario usuario = crearUsuario();
        when(usuarioRepository.findById(usuario.getId_usuario())).thenReturn(Optional.of(usuario));

        UsuarioDTO.Response resultado = usuarioService.buscarPorId(usuario.getId_usuario());

        assertNotNull(resultado);
        assertEquals(usuario.getId_usuario(), resultado.getId_usuario());
        assertEquals(usuario.getNombre(), resultado.getNombre());
    }

    @Test
    void guardarUsuario() {
        UsuarioDTO.Request request = crearRequest();
        Genero genero = crearGenero();
        RolPermiso rol = crearRolPermiso();
        Usuario usuarioGuardado = crearUsuario();

        when(generoRepository.findById(request.getId_genero())).thenReturn(Optional.of(genero));
        when(rolPermisoRepository.findById(request.getId_rol())).thenReturn(Optional.of(rol));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        UsuarioDTO.Response resultado = usuarioService.guardar(request);

        assertNotNull(resultado);
        assertEquals(usuarioGuardado.getNombre(), resultado.getNombre());
        assertEquals(usuarioGuardado.getCorreo(), resultado.getCorreo());
        assertEquals(usuarioGuardado.getTelefono(), resultado.getTelefono());
    }

    @Test
    void actualizarUsuario() {
        UsuarioDTO.Request request = crearRequest();
        Usuario usuarioExistente = crearUsuario();
        Genero genero = crearGenero();
        RolPermiso rol = crearRolPermiso();
        Usuario usuarioActualizado = crearUsuario();

        when(usuarioRepository.findById(usuarioExistente.getId_usuario())).thenReturn(Optional.of(usuarioExistente));
        when(generoRepository.findById(request.getId_genero())).thenReturn(Optional.of(genero));
        when(rolPermisoRepository.findById(request.getId_rol())).thenReturn(Optional.of(rol));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioActualizado);

        UsuarioDTO.Response resultado = usuarioService.actualizar(usuarioExistente.getId_usuario(), request);

        assertNotNull(resultado);
        assertEquals(usuarioActualizado.getNombre(), resultado.getNombre());
        assertEquals(usuarioActualizado.getCorreo(), resultado.getCorreo());
    }

    @Test
    void eliminarUsuario() {
        int id = 1;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.eliminar(id);

        verify(usuarioRepository).deleteById(id);
    }

    private UsuarioDTO.Request crearRequest() {
        return new UsuarioDTO.Request(
                1,
                "Usuario prueba",
                "prueba@dominio.com",
                "1234567890",
                1,
                1,
                true
        );
    }

    private Genero crearGenero() {
        Genero genero = new Genero();
        genero.setId_genero(1);
        genero.setNombre_genero("Masculino");
        return genero;
    }

    private RolPermiso crearRolPermiso() {
        RolPermiso rol = new RolPermiso();
        rol.setId(1);
        rol.setId_rol(1);
        rol.setId_permiso(1);
        rol.setNombre_rol("ADMIN");
        rol.setModulo("USUARIOS");
        rol.setAccion("CREAR");
        return rol;
    }

    private Usuario crearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNombre("Usuario prueba");
        usuario.setCorreo("prueba@dominio.com");
        usuario.setTelefono("1234567890");
        usuario.setGenero(crearGenero());
        usuario.setRol(crearRolPermiso());
        usuario.setIdTienda(1);
        usuario.setEstado(true);
        return usuario;
    }
}
