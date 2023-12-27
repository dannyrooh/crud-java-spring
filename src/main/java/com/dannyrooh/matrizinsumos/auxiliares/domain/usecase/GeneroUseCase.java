package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;

public interface GeneroUseCase {

    public GeneroDTO insert(GeneroDTO grupo);

    public GeneroDTO update(GeneroDTO grupo);

    public Boolean delete(int id);

    public GeneroDTO getById(int id);

    public List<GeneroDTO> getAll();

}
