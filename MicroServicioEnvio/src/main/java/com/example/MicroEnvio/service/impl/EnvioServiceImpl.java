package com.example.MicroEnvio.service.impl;

import com.example.MicroEnvio.dto.EnvioDTO;
import com.example.MicroEnvio.model.*;
import com.example.MicroEnvio.repository.*;
import com.example.MicroEnvio.service.EnvioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnvioServiceImpl implements EnvioService {

    private final EnvioRepository envioRepository;
    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final RutaEntregaRepository rutaEntregaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO.Response> listarTodos() {
        log.info("[ms-envio] Listando todos los envíos");
        return envioRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO.Response> listarActivos() {
        return envioRepository.findByEstado(true).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO.Response> listarPorCliente(int id_cliente) {
        return envioRepository.findByClienteId(id_cliente).stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EnvioDTO.Response buscarPorId(int id) {
        log.info("[ms-envio] Buscando envío id: {}", id);
        Envio e = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con id: " + id));
        return mapToResponse(e);
    }

    @Override
    @Transactional
    public EnvioDTO.Response crear(EnvioDTO.Request request) {
        log.info("[ms-envio] Creando envío");
        Venta venta = ventaRepository.findById(request.getId_venta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + request.getId_venta()));
        Cliente cliente = clienteRepository.findById(request.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getId_cliente()));
        Proveedor proveedor = proveedorRepository.findById(request.getId_provedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + request.getId_provedor()));
        RutaEntrega ruta = rutaEntregaRepository.findById(request.getId_ruta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id: " + request.getId_ruta()));

        Envio e = new Envio();
        e.setVenta(venta);
        e.setCliente(cliente);
        e.setProvedor(proveedor);
        e.setRuta(ruta);
        e.setEstado(request.isEstado());
        e.setFecha_despacho(request.getFecha_despacho());
        e.setFecha_entrega_est(request.getFecha_entrega_est());

        return mapToResponse(envioRepository.save(e));
    }

    @Override
    @Transactional
    public EnvioDTO.Response actualizar(int id, EnvioDTO.Request request) {
        Envio e = envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado con id: " + id));

        Venta venta = ventaRepository.findById(request.getId_venta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + request.getId_venta()));
        Cliente cliente = clienteRepository.findById(request.getId_cliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + request.getId_cliente()));
        Proveedor proveedor = proveedorRepository.findById(request.getId_provedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con id: " + request.getId_provedor()));
        RutaEntrega ruta = rutaEntregaRepository.findById(request.getId_ruta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id: " + request.getId_ruta()));

        e.setVenta(venta);
        e.setCliente(cliente);
        e.setProvedor(proveedor);
        e.setRuta(ruta);
        e.setEstado(request.isEstado());
        e.setFecha_despacho(request.getFecha_despacho());
        e.setFecha_entrega_est(request.getFecha_entrega_est());

        return mapToResponse(envioRepository.save(e));
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!envioRepository.existsById(id))
            throw new RuntimeException("Envío no encontrado con id: " + id);
        envioRepository.deleteById(id);
    }

    private EnvioDTO.Response mapToResponse(Envio e) {
        return new EnvioDTO.Response(
                e.getId(),
                e.getVenta().getId(),
                e.getCliente().getNombre(),
                e.getProvedor().getNombre(),
                e.getRuta().getDescripcion(),
                e.isEstado(),
                e.getFecha_despacho(),
                e.getFecha_entrega_est());
    }
}
