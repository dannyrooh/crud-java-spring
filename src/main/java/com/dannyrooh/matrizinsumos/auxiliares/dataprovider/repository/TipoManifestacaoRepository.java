package com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoManifestacao;

public interface TipoManifestacaoRepository extends JpaRepository<TipoManifestacao, Integer> {

    boolean existsByNomeIgnoreCase(String nome);

    boolean existsById(Long id);

    Optional<TipoManifestacao> findByNomeIgnoreCase(String nome);

    Integer findIdByNomeIgnoreCaseAndIdNot(String nome, Integer id);

}
