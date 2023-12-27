package com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Long id);

    Optional<Genero> findByNomeIgnoreCase(String nome);

    Integer findIdByNomeIgnoreCaseAndIdNot(String nome, Integer id);

}
