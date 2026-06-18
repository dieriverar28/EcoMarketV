package com.example.MicroProducto.service.impl;

import com.example.MicroProducto.dto.ProductoDTO;
import com.example.MicroProducto.entity.Categoria;
import com.example.MicroProducto.entity.Producto;
import com.example.MicroProducto.repository.CategoriaRepository;
import com.example.MicroProducto.repository.ProductoRepository;
import com.example.MicroProducto.service.ProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO.Response> listarTodos() {
        log.info("[ms-producto] Listando todos los productos");
        return productoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO.Response> listarPorCategoria(String categoria) {
        log.info("[ms-producto] Listando productos por categoria: {}", categoria);
        return productoRepository.findByCategoria_Nombre(categoria)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO.Response> listarActivos() {
        log.info("[ms-producto] Listando productos activos");
        return productoRepository.findByEstado(true)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO.Response buscarPorId(Long id) {
        log.info("[ms-producto] Buscando producto id: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[ms-producto] Producto no encontrado id: {}", id);
                    return new RuntimeException("Producto no encontrado con id: " + id);
                });
        return mapToResponse(producto);
    }

    @Override
    @Transactional
    public ProductoDTO.Response crear(ProductoDTO.Request request) {
        log.info("[ms-producto] Creando producto: {}", request.getNombre());

        if (productoRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RuntimeException("Ya existe un producto con el nombre: " + request.getNombre());
        }

        Categoria categoria = categoriaRepository.findByNombre(request.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + request.getCategoria()));

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setCategoria(categoria);
        producto.setPrecio_base(request.getPrecio_base());
        producto.setEstado(request.isEstado());

        Producto guardado = productoRepository.save(producto);
        log.info("[ms-producto] Producto creado id: {}", guardado.getId());
        return mapToResponse(guardado);
    }

    @Override
    @Transactional
    public ProductoDTO.Response actualizar(Long id, ProductoDTO.Request request) {
        log.info("[ms-producto] Actualizando producto id: {}", id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[ms-producto] Producto no encontrado id: {}", id);
                    return new RuntimeException("Producto no encontrado con id: " + id);
                });

        Categoria categoria = categoriaRepository.findByNombre(request.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + request.getCategoria()));

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setCategoria(categoria);
        producto.setPrecio_base(request.getPrecio_base());
        producto.setEstado(request.isEstado());

        Producto actualizado = productoRepository.save(producto);
        log.info("[ms-producto] Producto actualizado id: {}", actualizado.getId());
        return mapToResponse(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.info("[ms-producto] Eliminando producto id: {}", id);
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    private ProductoDTO.Response mapToResponse(Producto p) {
        return new ProductoDTO.Response(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getCategoria().getNombre(),
                p.getPrecio_base(),
                p.isEstado()
        );
    }
}