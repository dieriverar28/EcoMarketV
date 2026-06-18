package com.example.MicroTiendaUbicacion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.MicroTiendaUbicacion.dto.HorarioTiendaDTO;
import com.example.MicroTiendaUbicacion.entity.HorarioTienda;
import com.example.MicroTiendaUbicacion.entity.Tienda;
import com.example.MicroTiendaUbicacion.repository.HorarioTiendaRepository;
import com.example.MicroTiendaUbicacion.repository.TiendaRepository;
import com.example.MicroTiendaUbicacion.service.HorarioTiendaService;

@Service
public class HorarioTiendaServiceImpl implements HorarioTiendaService {

    private final HorarioTiendaRepository horarioRepository;
    private final TiendaRepository tiendaRepository;

    public HorarioTiendaServiceImpl(
            HorarioTiendaRepository horarioRepository,
            TiendaRepository tiendaRepository) {

        this.horarioRepository = horarioRepository;
        this.tiendaRepository = tiendaRepository;
    }

    @Override
    public List<HorarioTiendaDTO.Response> listar() {

        return horarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HorarioTiendaDTO.Response buscarPorId(Integer id) {

        HorarioTienda horario = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        return toResponse(horario);
    }

   @Override
    public HorarioTiendaDTO.Response guardar(HorarioTiendaDTO.Request request) {

    Tienda tienda = tiendaRepository.findById(request.getId_tienda())
            .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

    HorarioTienda horario = new HorarioTienda();

    horario.setTienda(tienda);

    horario.setDia_semana(
            HorarioTienda.DiaSemana.valueOf(
                    request.getDia_semana().toUpperCase()
            )
    );

    horario.setHora_apertura(request.getHora_apertura());
    horario.setHora_cierre(request.getHora_cierre());
    horario.setCerrado(request.getCerrado());

    return toResponse(horarioRepository.save(horario));
    }
    
    @Override
    public HorarioTiendaDTO.Response actualizar(Integer id, HorarioTiendaDTO.Request request) {

    HorarioTienda horario = horarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

    Tienda tienda = tiendaRepository.findById(request.getId_tienda())
            .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));

    horario.setTienda(tienda);

    horario.setDia_semana(
            HorarioTienda.DiaSemana.valueOf(
                    request.getDia_semana().toUpperCase()
            )
    );

    horario.setHora_apertura(request.getHora_apertura());
    horario.setHora_cierre(request.getHora_cierre());
    horario.setCerrado(request.getCerrado());

    return toResponse(horarioRepository.save(horario));
}
    @Override
    public void eliminar(Integer id) {
        horarioRepository.deleteById(id);
    }

    @Override
public List<HorarioTiendaDTO.Response> buscarPorTienda(Integer id_tienda) {

    return horarioRepository.findByTienda(id_tienda)
            .stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
}
   private HorarioTiendaDTO.Response toResponse(HorarioTienda horario) {

    return new HorarioTiendaDTO.Response(
            horario.getId_horario(),
            horario.getTienda().getId_tienda(),
            horario.getDia_semana().name(),
            horario.getHora_apertura(),
            horario.getHora_cierre(),
            horario.getCerrado()
    );
}
}