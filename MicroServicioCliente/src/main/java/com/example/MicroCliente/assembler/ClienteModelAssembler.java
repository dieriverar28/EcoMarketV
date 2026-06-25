package com.example.MicroCliente.assembler;

import com.example.MicroCliente.controller.ClienteController;
import com.example.MicroCliente.dto.ClienteDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteDTO.Response, EntityModel<ClienteDTO.Response>> {

    @Override
    public EntityModel<ClienteDTO.Response> toModel(ClienteDTO.Response cliente) {

        return EntityModel.of(cliente)
                .add(linkTo(methodOn(ClienteController.class)
                        .obtenerClientePorId(cliente.getId_cliente()))
                        .withSelfRel())

                .add(linkTo(methodOn(ClienteController.class)
                        .listarClientes())
                        .withRel("lista-clientes"));
    }
}