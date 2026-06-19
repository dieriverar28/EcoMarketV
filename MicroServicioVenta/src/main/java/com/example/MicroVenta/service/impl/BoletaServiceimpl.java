package com.example.MicroVenta.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroVenta.entity.Boleta;
import com.example.MicroVenta.repository.BoletaRepository;
import com.example.MicroVenta.service.BoletaService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoletaServiceimpl implements BoletaService {

    private final BoletaRepository boletaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> getBoletas() {
        return boletaRepository.findAll();
    }

    @Override
    @Transactional
    public Boleta saveBoletas(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    @Override
    @Transactional(readOnly = true)
    public Boleta getBoletaById(int id_boleta) {
        return boletaRepository.findById(id_boleta)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada con id: " + id_boleta));
    }

    @Override
    @Transactional
    public int deleteBoleta(int id_boleta) {
        if (!boletaRepository.existsById(id_boleta)) {
            return 0;
        }
        boletaRepository.deleteById(id_boleta);
        return 1;
    }
}

