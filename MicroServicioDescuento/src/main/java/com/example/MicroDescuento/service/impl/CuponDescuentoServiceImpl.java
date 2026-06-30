package com.example.MicroDescuento.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.MicroDescuento.dto.CuponDescuentoDTO;
import com.example.MicroDescuento.entity.CuponDescuento;
import com.example.MicroDescuento.repository.CuponDescuentoRepository;
import com.example.MicroDescuento.service.CuponDescuentoService;

@Service
@Transactional
public class CuponDescuentoServiceImpl implements CuponDescuentoService {

    private static final Logger log = LoggerFactory.getLogger(CuponDescuentoServiceImpl.class);

    private final CuponDescuentoRepository cuponDescuentoRepository;

    public CuponDescuentoServiceImpl(CuponDescuentoRepository cuponDescuentoRepository) {
        this.cuponDescuentoRepository = cuponDescuentoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuponDescuentoDTO.Response> getAllCupones() {
        log.info("Listando cupones de descuento");
        return cuponDescuentoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CuponDescuentoDTO.Response getCuponDescuentoById(int id_cupon_descuento) {
        log.info("Buscando cupón de descuento con id {}", id_cupon_descuento);
        CuponDescuento cupon = cuponDescuentoRepository.findById(id_cupon_descuento)
                .orElseThrow(() -> new RuntimeException("Cupon de descuento no encontrado con id: " + id_cupon_descuento));
        return mapToResponse(cupon);
    }

    @Override
    @Transactional
    public CuponDescuentoDTO.Response saveCuponDescuento(CuponDescuento cuponDescuento) {
        log.info("Creando cupón de descuento con código {}", cuponDescuento.getCodigo());
        return mapToResponse(cuponDescuentoRepository.save(cuponDescuento));
    }

    @Override
    @Transactional
    public int updateCuponDescuento(CuponDescuento cuponDescuento) {
        log.info("Actualizando cupón de descuento id {}", cuponDescuento.getId_cupon_descuento());
        if (!cuponDescuentoRepository.existsById(cuponDescuento.getId_cupon_descuento())) {
            throw new RuntimeException("Cupon de descuento no encontrado con id: " + cuponDescuento.getId_cupon_descuento());
        }
        cuponDescuentoRepository.save(cuponDescuento);
        return 1;
    }

    @Override
    @Transactional
    public int deleteCuponDescuento(int id_cupon_descuento) {
        log.info("Eliminando cupón de descuento id {}", id_cupon_descuento);
        if (!cuponDescuentoRepository.existsById(id_cupon_descuento)) {
            throw new RuntimeException("Cupon de descuento no encontrado con id: " + id_cupon_descuento);
        }
        cuponDescuentoRepository.deleteById(id_cupon_descuento);
        return 1;
    }

    private CuponDescuentoDTO.Response mapToResponse(CuponDescuento cuponDescuento) {
        return new CuponDescuentoDTO.Response(
                cuponDescuento.getId_cupon_descuento(),
                cuponDescuento.getCodigo(),
                cuponDescuento.getDescuento_pct(),
                cuponDescuento.getDescuento_monto(),
                cuponDescuento.getFecha_expiracion(),
                cuponDescuento.isActivo()
        );
    }
}

