package com.example.MicroPedido.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.dto.ProductoDTO;
import com.example.MicroPedido.entity.Categoria;
import com.example.MicroPedido.entity.Producto;
import com.example.MicroPedido.repository.CategoriaRepository;
import com.example.MicroPedido.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void listarProductos() {
        Categoria categoria = new Categoria(1, "Bebidas", "Líquidos", true);
        Producto producto = new Producto(1, "Agua", categoria, 1000, true);
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<ProductoDTO.Response> result = productoService.listarTodos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Agua");
    }

    @Test
    void listarProductosActivos() {
        Categoria categoria = new Categoria(2, "Comida", "Alimentos", true);
        Producto producto = new Producto(2, "Pan", categoria, 1500, true);
        when(productoRepository.findByEstado(true)).thenReturn(List.of(producto));

        List<ProductoDTO.Response> result = productoService.listarActivos();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).isEstado()).isTrue();
    }

    @Test
    void buscarProductoPorId() {
        Categoria categoria = new Categoria(3, "Hogar", "Útiles", true);
        Producto producto = new Producto(3, "Detergente", categoria, 2500, false);
        when(productoRepository.findById(3)).thenReturn(Optional.of(producto));

        ProductoDTO.Response result = productoService.buscarPorId(3);

        assertThat(result.getNombre()).isEqualTo("Detergente");
        assertThat(result.getPrecio_base()).isEqualTo(2500);
    }

    @Test
    void crearProducto() {
        ProductoDTO.Request request = new ProductoDTO.Request("Leche", 1, 1200, true);
        Categoria categoria = new Categoria(1, "Bebidas", "Líquidos", true);
        Producto productoGuardado = new Producto(4, "Leche", categoria, 1200, true);
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(org.mockito.ArgumentMatchers.any(Producto.class))).thenReturn(productoGuardado);

        ProductoDTO.Response result = productoService.crear(request);

        assertThat(result.getNombre()).isEqualTo("Leche");
        assertThat(result.getNombre_categoria()).isEqualTo("Bebidas");
    }

    @Test
    void actualizarProducto() {
        ProductoDTO.Request request = new ProductoDTO.Request("Café", 2, 3000, true);
        Categoria categoria = new Categoria(2, "Bebidas", "Líquidos", true);
        Producto producto = new Producto(5, "Antiguo", categoria, 1000, false);
        when(productoRepository.findById(5)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(producto)).thenReturn(producto);

        ProductoDTO.Response result = productoService.actualizar(5, request);

        assertThat(result.getNombre()).isEqualTo("Café");
        assertThat(result.getPrecio_base()).isEqualTo(3000);
        verify(productoRepository).save(producto);
    }

    @Test
    void eliminarProducto() {
        int result = productoService.eliminar(7);

        assertThat(result).isEqualTo(1);
        verify(productoRepository).deleteById(7);
    }
}
