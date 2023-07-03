package com.dh.projetoIntegrador.repository;

import com.dh.projetoIntegrador.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConsulaRepository extends JpaRepository<Consulta, Integer> {
    Optional<Consulta> findByDataHora (LocalDateTime dataHora);
}
