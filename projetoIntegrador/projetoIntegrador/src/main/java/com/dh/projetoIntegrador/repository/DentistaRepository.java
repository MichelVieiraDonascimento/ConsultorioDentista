package com.dh.projetoIntegrador.repository;

import com.dh.projetoIntegrador.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Integer> {

    Optional<Dentista> findJogadorByMatricula(String matricula);
}
