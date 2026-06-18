package com.example.MicroInventario.service;

import java.util.List;

import com.example.MicroInventario.entity.Producto;

public interface ProductoService {

    List<Producto> getProductos();

    Producto getProducto(int id_producto);

    Producto saveProducto(Producto producto);

    int updateProducto(Producto producto);

    int deleteProducto(int id_producto);
}