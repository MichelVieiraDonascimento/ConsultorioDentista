package com.dh.projetoIntegrador.repository;

import com.dh.projetoIntegrador.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findJogadorByNomeContainingIgnoreCase (String nome);
    Optional<Paciente> findJogadorByRgContainingIgnoreCase (String rg);
}
