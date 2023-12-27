package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;

public interface TipoRiscoPotencialUseCase {

    public TipoRiscoPotencialDTO insert(TipoRiscoPotencialDTO grupo);

    public TipoRiscoPotencialDTO update(TipoRiscoPotencialDTO grupo);

    public Boolean delete(int id);

    public TipoRiscoPotencialDTO getById(int id);

    public List<TipoRiscoPotencialDTO> getAll();

}
