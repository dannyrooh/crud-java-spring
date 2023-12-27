package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;

public interface TipoLixiviacaoUseCase {

    public TipoLixiviacaoDTO insert(TipoLixiviacaoDTO grupo);

    public TipoLixiviacaoDTO update(TipoLixiviacaoDTO grupo);

    public Boolean delete(int id);

    public TipoLixiviacaoDTO getById(int id);

    public List<TipoLixiviacaoDTO> getAll();

}
