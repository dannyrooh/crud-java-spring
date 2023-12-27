package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;

public interface TipoSoloUseCase {

    public TipoSoloDTO insert(TipoSoloDTO grupo);

    public TipoSoloDTO update(TipoSoloDTO grupo);

    public Boolean delete(int id);

    public TipoSoloDTO getById(int id);

    public List<TipoSoloDTO> getAll();

}
