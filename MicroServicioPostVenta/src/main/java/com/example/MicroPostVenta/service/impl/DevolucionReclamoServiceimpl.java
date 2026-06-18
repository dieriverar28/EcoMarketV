package com.example.MicroPostVenta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.example.MicroPostVenta.model.DevolucionReclamo;
import com.example.MicroPostVenta.repository.DevolucionReclamoRepository;
import com.example.MicroPostVenta.service.DevolucionReclamoService;

@Service
@Transactional
public class DevolucionReclamoServiceimpl implements DevolucionReclamoService {

    @Autowired
    private DevolucionReclamoRepository devolucionReclamoRepository;

    @Override
    public List<DevolucionReclamo> getDevolucionReclamos() {
        return devolucionReclamoRepository.findAll();
    }

    @Override
    public DevolucionReclamo getDevolucionReclamoById(int id_devolucion) {
        return devolucionReclamoRepository.findById(id_devolucion).orElse(new DevolucionReclamo());
    }

    @Override
    public DevolucionReclamo saveDevolucionReclamo(DevolucionReclamo devolucion) {
        return devolucionReclamoRepository.save(devolucion);
    }

    @Override
    public int updateDevolucionReclamo(DevolucionReclamo devolucion) {
        devolucionReclamoRepository.save(devolucion);
        return 1;
    }

    @Override
    public int deleteDevolucionReclamo(int id_devolucion) {
        if (devolucionReclamoRepository.existsById(id_devolucion)) {
            devolucionReclamoRepository.deleteById(id_devolucion);
            return 1;
        }
        return 0;
    }
}
