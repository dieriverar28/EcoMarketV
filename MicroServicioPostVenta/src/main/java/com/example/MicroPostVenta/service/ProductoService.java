package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.model.Producto;

public interface ProductoService {

    List<Producto> getProductos();

    Producto getProducto(int id_producto);

    Producto saveProducto(Producto producto);

    Producto updateProducto(int id_producto, Producto producto);

    int deleteProducto(int id_producto);
}
