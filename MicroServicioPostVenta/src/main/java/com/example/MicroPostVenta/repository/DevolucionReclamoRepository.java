package com.example.MicroPostVenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.MicroPostVenta.entity.DevolucionReclamo;

public interface DevolucionReclamoRepository extends JpaRepository<DevolucionReclamo, Integer> {
    @Query("SELECT d FROM DevolucionReclamo d")
    List<DevolucionReclamo> obtenerDevolucionReclamos();

    @Query("SELECT d FROM DevolucionReclamo d WHERE d.id_devolucion = :id_devolucion")
    DevolucionReclamo busDevolucionReclamo(@Param("id_devolucion") int id_devolucion);
}

