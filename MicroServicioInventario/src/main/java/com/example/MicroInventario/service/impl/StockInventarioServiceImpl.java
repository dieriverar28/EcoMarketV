package com.example.MicroInventario.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MicroInventario.dto.StockInventarioDTO;
import com.example.MicroInventario.entity.Producto;
import com.example.MicroInventario.entity.StockInventario;
import com.example.MicroInventario.entity.Tienda;
import com.example.MicroInventario.repository.ProductoRepository;
import com.example.MicroInventario.repository.StockInventarioRepository;
import com.example.MicroInventario.repository.TiendaRepository;
import com.example.MicroInventario.service.StockInventarioService;

@Service
public class StockInventarioServiceImpl implements StockInventarioService {

    @Autowired
    private StockInventarioRepository stockInventarioRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<StockInventarioDTO.Response> listar() {

        return stockInventarioRepository.findAll().stream().map(stock ->
                new StockInventarioDTO.Response(
                        stock.getId_stock(),
                        stock.getTienda().getId_tienda(),
                        stock.getProducto().getId_producto(),
                        stock.getCantidad(),
                        stock.getStock_min(),
                        stock.isEstado()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public StockInventarioDTO.Response buscarPorId(int id) {

        StockInventario stock = stockInventarioRepository.findById(id)
                .orElse(null);

        if (stock == null) {
            return null;
        }

        return new StockInventarioDTO.Response(
                stock.getId_stock(),
                stock.getTienda().getId_tienda(),
                stock.getProducto().getId_producto(),
                stock.getCantidad(),
                stock.getStock_min(),
                stock.isEstado()
        );
    }

    @Override
    public StockInventarioDTO.Response guardar(
            StockInventarioDTO.Request request) {

        Tienda tienda = tiendaRepository.findById(
                request.getId_tienda()).orElse(null);

        Producto producto = productoRepository.findById(
                request.getId_producto()).orElse(null);

        StockInventario stock = new StockInventario();

        stock.setTienda(tienda);
        stock.setProducto(producto);
        stock.setCantidad(request.getCantidad());
        stock.setStock_min(request.getStock_min());
        stock.setEstado(request.isEstado());

        StockInventario guardado =
                stockInventarioRepository.save(stock);

        return new StockInventarioDTO.Response(
                guardado.getId_stock(),
                guardado.getTienda().getId_tienda(),
                guardado.getProducto().getId_producto(),
                guardado.getCantidad(),
                guardado.getStock_min(),
                guardado.isEstado()
        );
    }

    @Override
    public StockInventarioDTO.Response actualizar(
            int id,
            StockInventarioDTO.Request request) {

        StockInventario stock =
                stockInventarioRepository.findById(id)
                        .orElse(null);

        if (stock == null) {
            return null;
        }

        Tienda tienda = tiendaRepository.findById(
                request.getId_tienda()).orElse(null);

        Producto producto = productoRepository.findById(
                request.getId_producto()).orElse(null);

        stock.setTienda(tienda);
        stock.setProducto(producto);
        stock.setCantidad(request.getCantidad());
        stock.setStock_min(request.getStock_min());
        stock.setEstado(request.isEstado());

        StockInventario actualizado =
                stockInventarioRepository.save(stock);

        return new StockInventarioDTO.Response(
                actualizado.getId_stock(),
                actualizado.getTienda().getId_tienda(),
                actualizado.getProducto().getId_producto(),
                actualizado.getCantidad(),
                actualizado.getStock_min(),
                actualizado.isEstado()
        );
    }

    @Override
    public void eliminar(int id) {

        stockInventarioRepository.deleteById(id);
    }
}