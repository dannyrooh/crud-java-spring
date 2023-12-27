package com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoPersistencia;

public interface TipoPersistenciaRepository extends JpaRepository<TipoPersistencia, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Long id);

    Optional<TipoPersistencia> findByNomeIgnoreCase(String nome);

    Integer findIdByNomeIgnoreCaseAndIdNot(String nome, Integer id);

}
