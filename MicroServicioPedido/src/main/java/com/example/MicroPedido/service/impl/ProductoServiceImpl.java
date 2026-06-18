package com.example.MicroPedido.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPedido.dto.ProductoDTO;
import com.example.MicroPedido.model.Categoria;
import com.example.MicroPedido.model.Producto;
import com.example.MicroPedido.repository.CategoriaRepository;
import com.example.MicroPedido.repository.ProductoRepository;
import com.example.MicroPedido.service.ProductoService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDTO.Response> listarTodos() {

        return productoRepository.findAll()
                .stream()
                .map(this::convertirResponse)
                .toList();
    }

    @Override
    public List<ProductoDTO.Response> listarActivos() {

        return productoRepository.findByEstado(true)
                .stream()
                .map(this::convertirResponse)
                .toList();
    }

    @Override
    public ProductoDTO.Response buscarPorId(int id) {

        Producto producto = productoRepository.findById(id)
                .orElse(new Producto());

        return convertirResponse(producto);
    }

    @Override
    public ProductoDTO.Response crear(ProductoDTO.Request request) {

        Categoria categoria = categoriaRepository.findById(request.getId_categoria())
                .orElse(new Categoria());

        Producto producto = new Producto();

        producto.setNombre(request.getNombre());
        producto.setCategoria(categoria);
        producto.setPrecio_base(request.getPrecio_base());
        producto.setEstado(request.isEstado());

        Producto guardado = productoRepository.save(producto);

        return convertirResponse(guardado);
    }

    @Override
    public ProductoDTO.Response actualizar(int id, ProductoDTO.Request request) {

        Producto producto = productoRepository.findById(id)
                .orElse(new Producto());

        Categoria categoria = categoriaRepository.findById(request.getId_categoria())
                .orElse(new Categoria());

        producto.setId_producto(id);
        producto.setNombre(request.getNombre());
        producto.setCategoria(categoria);
        producto.setPrecio_base(request.getPrecio_base());
        producto.setEstado(request.isEstado());

        Producto actualizado = productoRepository.save(producto);

        return convertirResponse(actualizado);
    }

    @Override
    public int eliminar(int id) {

        productoRepository.deleteById(id);
        return 1;
    }

    private ProductoDTO.Response convertirResponse(Producto producto) {

        return new ProductoDTO.Response(
                producto.getId_producto(),
                producto.getNombre(),
                producto.getCategoria().getId_categoria(),
                producto.getCategoria().getNombre(),
                producto.getPrecio_base(),
                producto.isEstado());
    }
}