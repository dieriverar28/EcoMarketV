package com.example.MicroPedido.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.MicroPedido.entity.Categoria;
import com.example.MicroPedido.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Test
    void listarCategorias() {
        Categoria categoria = new Categoria(1, "Bebidas", "Líquidos", true);
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));

        List<Categoria> result = categoriaService.getCategorias();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNombre()).isEqualTo("Bebidas");
    }

    @Test
    void buscarCategoriaPorId() {
        Categoria categoria = new Categoria(2, "Comida", "Alimentos", true);
        when(categoriaRepository.findById(2)).thenReturn(Optional.of(categoria));

        Categoria result = categoriaService.getCategoria(2);

        assertThat(result.getId_categoria()).isEqualTo(2);
        assertThat(result.getNombre()).isEqualTo("Comida");
    }

    @Test
    void guardarCategoria() {
        Categoria categoria = new Categoria(3, "Hogar", "Artículos", true);
        when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria result = categoriaService.saveCategoria(categoria);

        assertThat(result).isEqualTo(categoria);
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void actualizarCategoria() {
        Categoria categoria = new Categoria(4, "Tecnología", "Electrónica", true);

        int result = categoriaService.updateCategoria(categoria);

        assertThat(result).isEqualTo(1);
        verify(categoriaRepository).save(categoria);
    }

    @Test
    void eliminarCategoria() {
        int result = categoriaService.deleteCategoria(5);

        assertThat(result).isEqualTo(1);
        verify(categoriaRepository).deleteById(5);
    }
}
