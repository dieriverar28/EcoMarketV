package com.example.MicroProducto.service.impl;

import com.example.MicroProducto.dto.ProductoDTO;
import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Categoria categoria;
    private Producto producto;
    private ProductoDTO.Request request;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId_categoria(1);
        categoria.setNombre("Frutas");
        categoria.setDescripcion("Productos frutales");
        categoria.setEstado(true);

        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Manzana");
        producto.setDescripcion("Manzana roja");
        producto.setCategoria(categoria);
        producto.setPrecio_base(1000);
        producto.setEstado(true);

        request = new ProductoDTO.Request();
        request.setNombre("Manzana");
        request.setDescripcion("Manzana roja");
        request.setCategoria("Frutas");
        request.setPrecio_base(1000);
        request.setEstado(true);
    }

    // ---------- listarTodos ----------

    @Test
    void listarTodos_deberiaRetornarListaDeProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<ProductoDTO.Response> resultado = productoService.listarTodos();

        assertThat(resultado).hasSize(1);
        assertEquals("Manzana", resultado.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void listarTodos_deberiaRetornarListaVacia_siNoHayProductos() {
        when(productoRepository.findAll()).thenReturn(List.of());

        List<ProductoDTO.Response> resultado = productoService.listarTodos();

        assertThat(resultado).isEmpty();
    }

    // ---------- listarPorCategoria ----------

    @Test
    void listarPorCategoria_deberiaRetornarProductosFiltrados() {
        when(productoRepository.findByCategoria_Nombre("Frutas")).thenReturn(List.of(producto));

        List<ProductoDTO.Response> resultado = productoService.listarPorCategoria("Frutas");

        assertThat(resultado).hasSize(1);
        assertEquals("Frutas", resultado.get(0).getCategoria());
        verify(productoRepository).findByCategoria_Nombre("Frutas");
    }

    // ---------- listarActivos ----------

    @Test
    void listarActivos_deberiaRetornarSoloProductosActivos() {
        when(productoRepository.findByEstado(true)).thenReturn(List.of(producto));

        List<ProductoDTO.Response> resultado = productoService.listarActivos();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).isEstado()).isTrue();
        verify(productoRepository).findByEstado(true);
    }

    // ---------- buscarPorId ----------

    @Test
    void buscarPorId_deberiaRetornarProducto_siExiste() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        ProductoDTO.Response resultado = productoService.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Manzana", resultado.getNombre());
    }

    @Test
    void buscarPorId_deberiaLanzarExcepcion_siNoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.buscarPorId(99L));

        assertThat(ex.getMessage()).contains("Producto no encontrado con id: 99");
    }

    // ---------- crear ----------

    @Test
    void crear_deberiaGuardarYRetornarProducto_siDatosValidos() {
        when(productoRepository.existsByNombreIgnoreCase("Manzana")).thenReturn(false);
        when(categoriaRepository.findByNombre("Frutas")).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        ProductoDTO.Response resultado = productoService.crear(request);

        assertEquals("Manzana", resultado.getNombre());
        assertEquals("Frutas", resultado.getCategoria());
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void crear_deberiaLanzarExcepcion_siNombreYaExiste() {
        when(productoRepository.existsByNombreIgnoreCase("Manzana")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.crear(request));

        assertThat(ex.getMessage()).contains("Ya existe un producto con el nombre");
        verify(productoRepository, never()).save(any());
        verify(categoriaRepository, never()).findByNombre(anyString());
    }

    @Test
    void crear_deberiaLanzarExcepcion_siCategoriaNoExiste() {
        when(productoRepository.existsByNombreIgnoreCase("Manzana")).thenReturn(false);
        when(categoriaRepository.findByNombre("Frutas")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.crear(request));

        assertThat(ex.getMessage()).contains("Categoría no encontrada");
        verify(productoRepository, never()).save(any());
    }

    // ---------- actualizar ----------

    @Test
    void actualizar_deberiaActualizarYRetornarProducto_siExiste() {
        ProductoDTO.Request nuevoRequest = new ProductoDTO.Request();
        nuevoRequest.setNombre("Manzana Verde");
        nuevoRequest.setDescripcion("Manzana verde acida");
        nuevoRequest.setCategoria("Frutas");
        nuevoRequest.setPrecio_base(1200);
        nuevoRequest.setEstado(true);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findByNombre("Frutas")).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductoDTO.Response resultado = productoService.actualizar(1L, nuevoRequest);

        assertEquals("Manzana Verde", resultado.getNombre());
        assertEquals(1200, resultado.getPrecio_base());
        verify(productoRepository).save(producto);
    }

    @Test
    void actualizar_deberiaLanzarExcepcion_siProductoNoExiste() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.actualizar(99L, request));

        assertThat(ex.getMessage()).contains("Producto no encontrado con id: 99");
        verify(productoRepository, never()).save(any());
    }

    @Test
    void actualizar_deberiaLanzarExcepcion_siCategoriaNoExiste() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(categoriaRepository.findByNombre("Frutas")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.actualizar(1L, request));

        assertThat(ex.getMessage()).contains("Categoría no encontrada");
        verify(productoRepository, never()).save(any());
    }

    // ---------- eliminar ----------

    @Test
    void eliminar_deberiaEliminarProducto_siExiste() {
        when(productoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1L);

        productoService.eliminar(1L);

        verify(productoRepository).deleteById(1L);
    }

    @Test
    void eliminar_deberiaLanzarExcepcion_siProductoNoExiste() {
        when(productoRepository.existsById(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> productoService.eliminar(99L));

        assertThat(ex.getMessage()).contains("Producto no encontrado con id: 99");
        verify(productoRepository, never()).deleteById(any());
    }
}