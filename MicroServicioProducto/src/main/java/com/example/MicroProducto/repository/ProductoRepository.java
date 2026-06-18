package com.example.MicroProducto.repository;

import com.example.MicroProducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoria_Nombre(String nombre);

    List<Producto> findByEstado(boolean estado);

    boolean existsByNombreIgnoreCase(String nombre);
}
