package com.example.MicroPostVenta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.entity.Producto;
import com.example.MicroPostVenta.entity.ResenaCalificacion;
import com.example.MicroPostVenta.repository.ResenaCalificacionRepository;

public class ResenaCalificacionServiceImplTest {

    @Mock
    private ResenaCalificacionRepository resenaCalificacionRepository;

    @InjectMocks
    private ResenaCalificacionServiceimpl resenaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarResenas() {
        ResenaCalificacion resena = crearResena();
        when(resenaCalificacionRepository.obtenerResenaCalificacion()).thenReturn(List.of(resena));

        List<ResenaCalificacion> resultado = resenaService.getResenaCalificaciones();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(resena.getMotivo(), resultado.get(0).getMotivo());
    }

    @Test
    void buscarResenaPorId() {
        ResenaCalificacion resena = crearResena();
        when(resenaCalificacionRepository.buscarResenaCalificacion(resena.getId_resena())).thenReturn(resena);

        ResenaCalificacion resultado = resenaService.getResenaCalificacion(resena.getId_resena());

        assertNotNull(resultado);
        assertEquals(resena.getId_resena(), resultado.getId_resena());
    }

    @Test
    void guardarResena() {
        ResenaCalificacion resena = crearResena();
        when(resenaCalificacionRepository.save(any(ResenaCalificacion.class))).thenReturn(resena);

        ResenaCalificacion resultado = resenaService.saveResenaCalificacion(resena);

        assertNotNull(resultado);
        assertEquals(resena.getMotivo(), resultado.getMotivo());
    }

    @Test
    void actualizarResena() {
        ResenaCalificacion resena = crearResena();
        when(resenaCalificacionRepository.buscarResenaCalificacion(resena.getId_resena())).thenReturn(resena);
        when(resenaCalificacionRepository.save(any(ResenaCalificacion.class))).thenReturn(resena);

        ResenaCalificacion resultado = resenaService.updateResenaCalificacion(resena.getId_resena(), resena);

        assertNotNull(resultado);
        assertEquals(resena.getId_resena(), resultado.getId_resena());
    }

    @Test
    void eliminarResena() {
        int id = 1;
        when(resenaCalificacionRepository.existsById(id)).thenReturn(true);
        doNothing().when(resenaCalificacionRepository).deleteById(id);

        int resultado = resenaService.deleteResenaCalificacion(id);

        assertEquals(1, resultado);
        verify(resenaCalificacionRepository).deleteById(id);
    }

    private ResenaCalificacion crearResena() {
        ResenaCalificacion resena = new ResenaCalificacion();
        resena.setId_resena(1);
        resena.setCliente(crearCliente());
        resena.setProducto(crearProducto());
        resena.setMotivo("Buen producto");
        resena.setEstado(true);
        resena.setFecha(Date.valueOf("2026-12-31"));
        return resena;
    }

    private Cliente crearCliente() {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Cliente prueba");
        cliente.setEmail("cliente@prueba.com");
        cliente.setTelefono("1234567890");
        cliente.setComuna(1);
        cliente.setDireccion_envio("Direccion prueba");
        cliente.setGenero(1);
        return cliente;
    }

    private Producto crearProducto() {
        Producto producto = new Producto();
        producto.setId_producto(1);
        producto.setNombre("Producto prueba");
        producto.setDescripcion("Descripcion prueba");
        producto.setCategoria("Categoria prueba");
        producto.setPrecio_base(10000);
        producto.setEstado(true);
        return producto;
    }
}

