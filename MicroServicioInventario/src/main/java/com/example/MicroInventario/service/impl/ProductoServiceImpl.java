package com.example.MicroInventario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroInventario.entity.Producto;
import com.example.MicroInventario.repository.ProductoRepository;
import com.example.MicroInventario.service.ProductoService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProducto(Long id_producto) {
        return productoRepository.findById(id_producto)
                .orElse(new Producto());
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public int updateProducto(Producto producto) {
        productoRepository.save(producto);
        return 1;
    }

    @Override
    public int deleteProducto(Long id_producto) {
        productoRepository.deleteById(id_producto);
        return 1;
    }
}
