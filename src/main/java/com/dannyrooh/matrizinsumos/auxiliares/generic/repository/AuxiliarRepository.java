package com.dannyrooh.matrizinsumos.auxiliares.generic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuxiliarRepository<M, ID> extends JpaRepository<M, ID> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(ID i);

    Optional<M> findByNomeIgnoreCase(String nome);

    ID findIdByNomeIgnoreCaseAndIdNot(String nome, ID i);

    // void deleteById(Integer id);

    // Optional<M> findById(Integer id);

}
