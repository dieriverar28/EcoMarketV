package com.example.MicroVenta.service;


import java.util.List;

import com.example.MicroVenta.dto.BoletaDTO;
import com.example.MicroVenta.model.Boleta;

import jakarta.validation.Valid;


public interface BoletaService {
    
    List<Boleta> getBoletas();
    Boleta saveBoletas(Boleta boleta);
    Boleta getBoletaById(int id_boleta);
    int deleteBoleta(int id_boleta);
}
