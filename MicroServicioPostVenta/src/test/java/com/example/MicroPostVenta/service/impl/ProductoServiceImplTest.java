package com.example.MicroPostVenta.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.MicroPostVenta.entity.Producto;
import com.example.MicroPostVenta.repository.ProductoRepository;

public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceimpl productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductos() {
        Producto producto = crearProducto();
        when(productoRepository.obtenerProductos()).thenReturn(List.of(producto));

        List<Producto> resultado = productoService.getProductos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(producto.getNombre(), resultado.get(0).getNombre());
    }

    @Test
    void buscarProductoPorId() {
        Producto producto = crearProducto();
        when(productoRepository.buscarProducto(producto.getId_producto())).thenReturn(producto);

        Producto resultado = productoService.getProducto(producto.getId_producto());

        assertNotNull(resultado);
        assertEquals(producto.getId_producto(), resultado.getId_producto());
    }

    @Test
    void guardarProducto() {
        Producto producto = crearProducto();
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.saveProducto(producto);

        assertNotNull(resultado);
        assertEquals(producto.getCategoria(), resultado.getCategoria());
    }

    @Test
    void actualizarProducto() {
        Producto producto = crearProducto();
        when(productoRepository.buscarProducto(producto.getId_producto())).thenReturn(producto);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        Producto resultado = productoService.updateProducto(producto.getId_producto(), producto);

        assertNotNull(resultado);
        assertEquals(producto.getPrecio_base(), resultado.getPrecio_base());
    }

    @Test
    void eliminarProducto() {
        int id = 1;
        when(productoRepository.existsById(id)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(id);

        int resultado = productoService.deleteProducto(id);

        assertEquals(1, resultado);
        verify(productoRepository).deleteById(id);
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

