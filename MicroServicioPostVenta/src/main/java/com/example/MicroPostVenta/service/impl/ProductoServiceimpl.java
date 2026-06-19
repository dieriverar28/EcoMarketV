package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.entity.Producto;
import com.example.MicroPostVenta.repository.ProductoRepository;
import com.example.MicroPostVenta.service.ProductoService;

@Service
@Transactional
public class ProductoServiceimpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getProductos() {
        return productoRepository.obtenerProductos();
    }

    @Override
    public Producto getProducto(int id_producto) {
        Producto producto = productoRepository.buscarProducto(id_producto);
        if (producto != null) {
            return producto;
        }
        return new Producto();
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(int id_producto, Producto producto) {
        Producto productoExistente = getProducto(id_producto);
        if (productoExistente != null && productoExistente.getId_producto() != 0) {
            producto.setId_producto(id_producto);
            return productoRepository.save(producto);
        }
        return null;
    }

    @Override
    public int deleteProducto(int id_producto) {
        if (productoRepository.existsById(id_producto)) {
            productoRepository.deleteById(id_producto);
            return 1;
        }
        return 0;
    }
}

