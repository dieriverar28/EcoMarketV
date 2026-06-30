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

import com.example.MicroUsuarioSeguridad.entity.RolPermiso;
import com.example.MicroUsuarioSeguridad.repository.RolPermisoRepository;

public class RolPermisoServiceImplTest {

    @Mock
    private RolPermisoRepository rolPermisoRepository;

    @InjectMocks
    private RolPermisoServiceimpl rolPermisoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarRolPermisos() {
        RolPermiso rolPermiso = crearRolPermiso();
        when(rolPermisoRepository.findAll()).thenReturn(List.of(rolPermiso));

        List<RolPermiso> resultado = rolPermisoService.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(rolPermiso.getNombre_rol(), resultado.get(0).getNombre_rol());
    }

    @Test
    void buscarRolPermisoPorId() {
        RolPermiso rolPermiso = crearRolPermiso();
        when(rolPermisoRepository.findById(rolPermiso.getId())).thenReturn(Optional.of(rolPermiso));

        RolPermiso resultado = rolPermisoService.buscarPorId(rolPermiso.getId());

        assertNotNull(resultado);
        assertEquals(rolPermiso.getId(), resultado.getId());
        assertEquals(rolPermiso.getNombre_rol(), resultado.getNombre_rol());
    }

    @Test
    void guardarRolPermiso() {
        RolPermiso rolPermiso = crearRolPermiso();
        when(rolPermisoRepository.save(any(RolPermiso.class))).thenReturn(rolPermiso);

        RolPermiso resultado = rolPermisoService.guardar(rolPermiso);

        assertNotNull(resultado);
        assertEquals(rolPermiso.getNombre_rol(), resultado.getNombre_rol());
        assertEquals(rolPermiso.getModulo(), resultado.getModulo());
    }

    @Test
    void actualizarRolPermiso() {
        RolPermiso existente = crearRolPermiso();
        RolPermiso actualización = crearRolPermiso();
        actualización.setNombre_rol("ADMIN_ACTUALIZADO");
        actualización.setModulo("USUARIOS_ACTUALIZADO");
        actualización.setAccion("EDITAR");

        when(rolPermisoRepository.findById(existente.getId())).thenReturn(Optional.of(existente));
        when(rolPermisoRepository.save(any(RolPermiso.class))).thenReturn(actualización);

        RolPermiso resultado = rolPermisoService.actualizar(existente.getId(), actualización);

        assertNotNull(resultado);
        assertEquals(actualización.getNombre_rol(), resultado.getNombre_rol());
        assertEquals(actualización.getModulo(), resultado.getModulo());
        assertEquals(actualización.getAccion(), resultado.getAccion());
    }

    @Test
    void eliminarRolPermiso() {
        int id = 1;
        doNothing().when(rolPermisoRepository).deleteById(id);

        rolPermisoService.eliminar(id);

        verify(rolPermisoRepository).deleteById(id);
    }

    private RolPermiso crearRolPermiso() {
        RolPermiso rolPermiso = new RolPermiso();
        rolPermiso.setId(1);
        rolPermiso.setId_rol(1);
        rolPermiso.setId_permiso(1);
        rolPermiso.setNombre_rol("ADMIN");
        rolPermiso.setModulo("USUARIOS");
        rolPermiso.setAccion("CREAR");
        return rolPermiso;
    }
}

