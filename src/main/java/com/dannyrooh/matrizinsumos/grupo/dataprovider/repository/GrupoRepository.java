package com.dannyrooh.matrizinsumos.grupo.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dannyrooh.matrizinsumos.grupo.dataprovider.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Long id);

    Optional<Grupo> findByNomeIgnoreCase(String nome);

    Integer findIdByNomeIgnoreCaseAndIdNot(String nome, Integer id);

}
