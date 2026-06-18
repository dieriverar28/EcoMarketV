package com.example.MicroVenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.model.Factura;
import com.example.MicroVenta.repository.FacturaRepository;
import com.example.MicroVenta.service.FacturaService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacturaServiceimpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> listarTodos() {
        return facturaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Factura buscarPorId(int id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public Factura crear(Factura request) {
        return facturaRepository.save(request);
    }

    @Override
    @Transactional
    public Factura actualizar(int id, Factura request) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
        factura.setVenta(request.getVenta());
        factura.setFolio(request.getFolio());
        factura.setTimbre_electronico(request.getTimbre_electronico());
        factura.setRazon_social(request.getRazon_social());
        factura.setNumrun_cliente(request.getNumrun_cliente());
        factura.setDvrun_cliente(request.getDvrun_cliente());
        factura.setGiro(request.getGiro());
        factura.setFecha_emision(request.getFecha_emision());
        factura.setMonto_iva(request.getMonto_iva());
        factura.setMonto_total(request.getMonto_total());
        factura.setEmail_envio(request.getEmail_envio());
        return facturaRepository.save(factura);
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        if (!facturaRepository.existsById(id))
            throw new RuntimeException("Factura no encontrada con id: " + id);
        facturaRepository.deleteById(id);
    }

    @Override
    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    @Override
    public int saveFactura(Factura factura) {
        return facturaRepository.save(factura).getId_factura();
    }

    @Override
    public Factura getFactura(int id_factura) {
        return facturaRepository.findById(id_factura)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id_factura));
    }

    @Override
    public int deleteFactura(int id_factura) {
        if (!facturaRepository.existsById(id_factura)) {
            return 0;
        }
        facturaRepository.deleteById(id_factura);
        return 1;
    }
}
