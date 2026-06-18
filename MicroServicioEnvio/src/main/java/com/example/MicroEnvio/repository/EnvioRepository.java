package com.example.MicroEnvio.repository;

import com.example.MicroEnvio.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    List<Envio> findByEstado(boolean estado);
    List<Envio> findByClienteId(int id);
}
