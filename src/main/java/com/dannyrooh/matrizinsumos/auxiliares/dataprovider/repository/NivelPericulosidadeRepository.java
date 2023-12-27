package com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.NivelPericulosidade;

public interface NivelPericulosidadeRepository extends JpaRepository<NivelPericulosidade, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Long id);

    Optional<NivelPericulosidade> findByNomeIgnoreCase(String nome);

    Integer findIdByNomeIgnoreCaseAndIdNot(String nome, Integer id);

}
