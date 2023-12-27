package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;

public interface AtuacaoUseCase {

    public AtuacaoDTO insert(AtuacaoDTO atuacao);

    public AtuacaoDTO update(AtuacaoDTO atuacao);

    public Boolean delete(int id);

    public AtuacaoDTO getById(int id);

    public List<AtuacaoDTO> getAll();

}
