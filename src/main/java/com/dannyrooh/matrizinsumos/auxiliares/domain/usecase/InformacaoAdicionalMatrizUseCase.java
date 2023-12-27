package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;

public interface InformacaoAdicionalMatrizUseCase {

    public InformacaoAdicionalMatrizDTO insert(InformacaoAdicionalMatrizDTO grupo);

    public InformacaoAdicionalMatrizDTO update(InformacaoAdicionalMatrizDTO grupo);

    public Boolean delete(int id);

    public InformacaoAdicionalMatrizDTO getById(int id);

    public List<InformacaoAdicionalMatrizDTO> getAll();

}
