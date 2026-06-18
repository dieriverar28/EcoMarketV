package com.example.MicroInventario.service;

import java.util.List;

import com.example.MicroInventario.entity.Categoria;

public interface CategoriaService {

    List<Categoria> getCategorias();

    Categoria getCategoria(int id_categoria);

    Categoria saveCategoria(Categoria categoria);

    int updateCategoria(Categoria categoria);

    int deleteCategoria(int id_categoria);
}
