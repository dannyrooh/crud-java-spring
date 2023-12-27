package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;

public interface TipoInfluenciaSoloUseCase {

    public TipoInfluenciaSoloDTO insert(TipoInfluenciaSoloDTO grupo);

    public TipoInfluenciaSoloDTO update(TipoInfluenciaSoloDTO grupo);

    public Boolean delete(int id);

    public TipoInfluenciaSoloDTO getById(int id);

    public List<TipoInfluenciaSoloDTO> getAll();

}
