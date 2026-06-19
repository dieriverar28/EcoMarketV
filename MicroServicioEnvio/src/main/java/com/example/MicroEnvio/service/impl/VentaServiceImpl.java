package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.VentaDTO;
import com.example.MicroEnvio.entity.Venta;
import com.example.MicroEnvio.repository.VentaRepository;
import com.example.MicroEnvio.service.VentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VentaDTO.Response> listarTodos() {
        return ventaRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VentaDTO.Response buscarPorId(int id) {
        Venta v = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        return mapToResponse(v);
    }

    @Override
    @Transactional
    public VentaDTO.Response crear(VentaDTO.Request request) {
        Venta v = new Venta();
        v.setId_pedido(request.getId_pedido());
        v.setId_tienda(request.getId_tienda());
        v.setId_cliente(request.getId_cliente());
        v.setFecha_venta(request.getFecha_venta());
        v.setTotal_neto(request.getTotal_neto());
        v.setDescuento_aplicado(request.getDescuento_aplicado());
        v.setTipo_documento(request.getTipo_documento());
        return mapToResponse(ventaRepository.save(v));
    }

    @Override
    @Transactional
    public VentaDTO.Response actualizar(int id, VentaDTO.Request request) {
        Venta v = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        v.setId_pedido(request.getId_pedido());
        v.setId_tienda(request.getId_tienda());
        v.setId_cliente(request.getId_cliente());
        v.setFecha_venta(request.getFecha_venta());
        v.setTotal_neto(request.getTotal_neto());
        v.setDescuento_aplicado(request.getDescuento_aplicado());
        v.setTipo_documento(request.getTipo_documento());
        return mapToResponse(ventaRepository.save(v));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!ventaRepository.existsById(id))
            throw new RuntimeException("Venta no encontrada con id: " + id);
        ventaRepository.deleteById(id);
    }

    private VentaDTO.Response mapToResponse(Venta v) {
        return new VentaDTO.Response(
                v.getId(), v.getId_pedido(), v.getId_tienda(),
                v.getId_cliente(), v.getFecha_venta(), v.getTotal_neto(),
                v.getDescuento_aplicado(), v.getTipo_documento());
    }
}

