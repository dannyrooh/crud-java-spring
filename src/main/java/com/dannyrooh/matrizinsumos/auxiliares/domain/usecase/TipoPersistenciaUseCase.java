package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;

public interface TipoPersistenciaUseCase {

    public TipoPersistenciaDTO insert(TipoPersistenciaDTO grupo);

    public TipoPersistenciaDTO update(TipoPersistenciaDTO grupo);

    public Boolean delete(int id);

    public TipoPersistenciaDTO getById(int id);

    public List<TipoPersistenciaDTO> getAll();

}
