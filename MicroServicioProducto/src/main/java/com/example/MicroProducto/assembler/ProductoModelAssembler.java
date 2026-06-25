package com.example.MicroProducto.assembler;

import com.example.MicroProducto.controller.ProductoController;
import com.example.MicroProducto.dto.ProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductoDTO.Response, EntityModel<ProductoDTO.Response>> {

    @Override
    public EntityModel<ProductoDTO.Response> toModel(ProductoDTO.Response producto) {
        return EntityModel.of(producto)
                .add(linkTo(methodOn(ProductoController.class).buscarPorId(producto.getId())).withSelfRel())
                .add(linkTo(methodOn(ProductoController.class).listarTodos()).withRel("lista-productos"))
                .add(linkTo(methodOn(ProductoController.class).listarActivos()).withRel("productos-activos"))
                .add(linkTo(methodOn(ProductoController.class).listarPorCategoria(producto.getCategoria())).withRel("categoria"));
    }
}