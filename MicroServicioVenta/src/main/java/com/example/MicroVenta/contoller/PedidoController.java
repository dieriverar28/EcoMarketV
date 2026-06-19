package com.example.MicroVenta.contoller;

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

import com.example.MicroVenta.entity.Factura;
import com.example.MicroVenta.entity.Pedido;
import com.example.MicroVenta.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SuppressWarnings("unused")
@RestController
@Tag(name = "Pedido")
@RequestMapping("api/v2/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
            @Operation(summary = "Obtener Pedidos",description = "Obtener lista de pedidos")

    public List<Pedido> listarPedidos(){
        return pedidoService.getPedidos();
    }
    //agregar
    @PostMapping
        @Operation(summary = "Registrar Pedido",description = "Registra pedido existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "pedido registrado exitosamente",
                content = @Content(mediaType = "application/JSON",
                    schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404",description = "pedido no encontrado")
        })
    public int agregarPedido(@Valid @RequestBody int pedido){
        return pedidoService.savePedido(pedido);
     }
    //buscar
    @GetMapping("/{id_pedido}")
    @Operation(summary = "Buscar Pedido",description = "Busca pedido por ID")
    public int buscarPedido(@PathVariable int id_pedido){
        return pedidoService.savePedido(id_pedido);
    }
    //actualizar
    @PutMapping("/{id_pedido}")
    @Operation(summary = "Actualizar Pedido",description = "Actualiza pedido existente")
            @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "pedido actualizado exitosamente",
                    content = @Content(mediaType = "application/JSON",
                        schema = @Schema(implementation = Pedido.class))),
                @ApiResponse(responseCode = "404",description = "pedido no encontrado")
            })
    public int actualizarPedido(@PathVariable int id_pedido, @Valid @RequestBody int pedido){
        return (int) ( pedidoService).savePedido(pedido);
    }
    //eliminar
    @DeleteMapping("/{id_pedido}")
        @Operation(summary = "Eliminar Pedido",description = "Elimina pedido por ID")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "pedido eliminado exitosamente"),
            @ApiResponse(responseCode = "404",description = "pedido no encontrado")
        })
    public String eliminarPedido(@PathVariable int id_pedido){
        if (pedidoService.savePedido(id_pedido)== 1) {
            return "Pedido eliminado correctamente";
        }
        return "Error al eliminar el pedido";
    }

}

