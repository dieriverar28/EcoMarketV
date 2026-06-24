package com.example.MicroInventario.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "MS-PRODUCTO", url = "http://localhost:8083")
public interface ProductoClient {

    @GetMapping("/api/v1/productos")
    List<Object> listarProductos();

    @GetMapping("/api/v1/productos/activos")
    List<Object> listarProductosActivos();
}
