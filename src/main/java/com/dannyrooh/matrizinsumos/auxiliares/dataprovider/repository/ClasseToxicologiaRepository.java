package com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseToxicologia;

public interface ClasseToxicologiaRepository extends JpaRepository<ClasseToxicologia, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Long id);

    Optional<ClasseToxicologia> findByNomeIgnoreCase(String nome);

    Integer findIdByNomeIgnoreCaseAndIdNot(String nome, Integer id);

}
