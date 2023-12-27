package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;

public interface ModoAcaoUseCase {

    public ModoAcaoDTO insert(ModoAcaoDTO grupo);

    public ModoAcaoDTO update(ModoAcaoDTO grupo);

    public Boolean delete(int id);

    public ModoAcaoDTO getById(int id);

    public List<ModoAcaoDTO> getAll();

}
