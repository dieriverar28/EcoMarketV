package com.example.MicroPostVenta.service;

import java.util.List;

import com.example.MicroPostVenta.model.DevolucionReclamo;

public interface DevolucionReclamoService {

    List<DevolucionReclamo> getDevolucionReclamos();

    DevolucionReclamo getDevolucionReclamoById(int id_devolucion);

    DevolucionReclamo saveDevolucionReclamo(DevolucionReclamo devolucion);

    int updateDevolucionReclamo(DevolucionReclamo devolucion);

    int deleteDevolucionReclamo(int id_devolucion);
}
