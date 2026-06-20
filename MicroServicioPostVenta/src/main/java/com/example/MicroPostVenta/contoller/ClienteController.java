package com.example.MicroPostVenta.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MicroPostVenta.entity.Cliente;
import com.example.MicroPostVenta.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@SuppressWarnings("unused")
@RestController
@RequestMapping("api/v1/clientes")
@Tag(name = "Cliente", description = "Operaciones relacionadas con clientes")

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Obtener clientes", description = "Obtener lista de clientes")
    public List<Cliente> listarClientes() {
        return clienteService.obtenerClientes();
    }

    //agregar
    @PostMapping
    @Operation(summary = "Registrar cliente",description = "Registra cliente existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente registrada exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "404",description = "cliente no encontrado")
        })
    public Cliente agregarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.crearCliente(cliente);

    }
    //buscar
    @GetMapping("{id_cliente}")
    @Operation(summary = "Obtener cliente por ID", description = "Obtener cliente por ID")
    public Cliente buscarCliente(@PathVariable("id_cliente") int id_cliente) {
        return clienteService.buscarCliente(id_cliente);
    }

    //actualizar
    @PutMapping("{id_cliente}")
    @Operation(summary = "Actualizar Cliente",description = "Actualiza cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "cliente actualizado exitosamente",
            content = @Content(mediaType = "application/JSON",
                schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404",description = "cliente no encontrado")
    })
    public Cliente actualizarCliente(@PathVariable("id_cliente") int id_cliente, @Valid @RequestBody Cliente cliente) {
        cliente.setId_cliente(id_cliente);
        return clienteService.actualizarCliente(cliente);
    }

    //eliminar
    @DeleteMapping("{id_cliente}")
     @Operation(summary = "Eliminar cliente",description = "Elimina cliente por id")
    @ApiResponses(value  = {
        @ApiResponse(responseCode = "200", description = "cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404",description = "cliente no encontrado")
    })
    public String eliminarCliente(@PathVariable("id_cliente") int id_cliente) {
        if (clienteService.eliminarCliente(id_cliente) == 1) {
            return "Cliente eliminado correctamente";
        }
        return "Error al eliminar el cliente";
    }

}

