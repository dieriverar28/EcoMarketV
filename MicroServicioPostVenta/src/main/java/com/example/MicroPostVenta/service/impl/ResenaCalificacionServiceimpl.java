package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.entity.ResenaCalificacion;
import com.example.MicroPostVenta.repository.ResenaCalificacionRepository;
import com.example.MicroPostVenta.service.ResenaCalificacionService;

@Service
@Transactional
public class ResenaCalificacionServiceimpl implements ResenaCalificacionService {

    @Autowired
    private ResenaCalificacionRepository resenaCalificacionRepository;

    @Override
    public List<ResenaCalificacion> getResenaCalificaciones() {
        return resenaCalificacionRepository.obtenerResenaCalificacion();
    }

    @Override
    public ResenaCalificacion getResenaCalificacion(int id_resena_calificacion) {
        ResenaCalificacion resena = resenaCalificacionRepository.buscarResenaCalificacion(id_resena_calificacion);
        if (resena != null) {
            return resena;
        }
        return new ResenaCalificacion();
    }

    @Override
    public ResenaCalificacion saveResenaCalificacion(ResenaCalificacion resena) {
        return resenaCalificacionRepository.save(resena);
    }

    @Override
    public ResenaCalificacion updateResenaCalificacion(int id_resena_calificacion, ResenaCalificacion resena) {
        ResenaCalificacion resenaExistente = getResenaCalificacion(id_resena_calificacion);
        if (resenaExistente != null && resenaExistente.getId_resena() != 0) {
            resena.setId_resena(id_resena_calificacion);
            return resenaCalificacionRepository.save(resena);
        }
        return null;
    }

    @Override
    public int deleteResenaCalificacion(int id_resena_calificacion) {
        if (resenaCalificacionRepository.existsById(id_resena_calificacion)) {
            resenaCalificacionRepository.deleteById(id_resena_calificacion);
            return 1;
        }
        return 0;
    }
}

