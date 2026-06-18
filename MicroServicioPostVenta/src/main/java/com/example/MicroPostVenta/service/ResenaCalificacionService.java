package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.model.ResenaCalificacion;

public interface ResenaCalificacionService {

    List<ResenaCalificacion> getResenaCalificaciones();

    ResenaCalificacion getResenaCalificacion(int id_resena_calificacion);

    ResenaCalificacion saveResenaCalificacion(ResenaCalificacion resenaCalificacion);

    ResenaCalificacion updateResenaCalificacion(int id_resena_calificacion, ResenaCalificacion resenaCalificacion);

    int deleteResenaCalificacion(int id_resena_calificacion);
}
