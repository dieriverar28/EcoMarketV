package com.example.MicroPedido.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroPedido.entity.Categoria;
import com.example.MicroPedido.repository.CategoriaRepository;
import com.example.MicroPedido.service.CategoriaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoria(int id_categoria) {

        return categoriaRepository.findById(id_categoria)
                .orElse(new Categoria());
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public int updateCategoria(Categoria categoria) {

        categoriaRepository.save(categoria);
        return 1;
    }

    @Override
    public int deleteCategoria(int id_categoria) {

        categoriaRepository.deleteById(id_categoria);
        return 1;
    }
}
