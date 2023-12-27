package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;

public interface EpocaAplicacaoUseCase {

    public EpocaAplicacaoDTO insert(EpocaAplicacaoDTO grupo);

    public EpocaAplicacaoDTO update(EpocaAplicacaoDTO grupo);

    public Boolean delete(int id);

    public EpocaAplicacaoDTO getById(int id);

    public List<EpocaAplicacaoDTO> getAll();

}
