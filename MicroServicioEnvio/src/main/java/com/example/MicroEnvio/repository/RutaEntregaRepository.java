package com.example.MicroEnvio.repository;

import com.example.MicroEnvio.entity.RutaEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaEntregaRepository extends JpaRepository<RutaEntrega, Integer> {
}

