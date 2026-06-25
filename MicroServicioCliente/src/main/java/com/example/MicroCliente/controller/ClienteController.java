package com.example.MicroCliente.controller;

import com.example.MicroCliente.assembler.ClienteModelAssembler;
import com.example.MicroCliente.dto.ClienteDTO;
import com.example.MicroCliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Obtiene la lista de todos los clientes")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO.Response>>> listarClientes() {

        List<EntityModel<ClienteDTO.Response>> clientes =
                clienteService.listarClientes()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(clientes)
                        .add(linkTo(methodOn(ClienteController.class)
                                .listarClientes())
                                .withSelfRel())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Obtiene un cliente según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<EntityModel<ClienteDTO.Response>> obtenerClientePorId(@PathVariable Integer id) {

        return ResponseEntity.ok(
                assembler.toModel(
                        clienteService.obtenerClientePorId(id)
                )
        );
    }

    @GetMapping("/genero/{id_genero}")
    @Operation(summary = "Listar clientes por género",
            description = "Obtiene todos los clientes de un género específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })
    public ResponseEntity<CollectionModel<EntityModel<ClienteDTO.Response>>> listarClientesPorGenero(
            @PathVariable Integer id_genero) {

        List<EntityModel<ClienteDTO.Response>> clientes =
                clienteService.listarClientesPorGenero(id_genero)
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(clientes)
                        .add(linkTo(methodOn(ClienteController.class)
                                .listarClientesPorGenero(id_genero))
                                .withSelfRel())
                        .add(linkTo(methodOn(ClienteController.class)
                                .listarClientes())
                                .withRel("todos-los-clientes"))
        );
    }

    @PostMapping
    @Operation(summary = "Crear cliente", description = "Registra un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Género no encontrado")
    })
    public ResponseEntity<EntityModel<ClienteDTO.Response>> crearCliente(
            @Valid @RequestBody ClienteDTO.Request request) {

        ClienteDTO.Response response =
                clienteService.crearCliente(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        assembler.toModel(response)
                                .add(linkTo(methodOn(ClienteController.class)
                                        .listarClientes())
                                        .withRel("lista-clientes"))
                );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente",
            description = "Actualiza un cliente existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<EntityModel<ClienteDTO.Response>> actualizarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteDTO.Request request) {

        ClienteDTO.Response response =
                clienteService.actualizarCliente(id, request);

        return ResponseEntity.ok(
                assembler.toModel(response)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {

        clienteService.eliminarCliente(id);

        return ResponseEntity.noContent().build();
    }
}