package com.dannyrooh.matrizinsumos.grupo.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;

public interface GrupoUseCase {

    public GrupoDTO insert(GrupoDTO grupo);

    public GrupoDTO update(GrupoDTO grupo);

    public Boolean delete(int id);

    public GrupoDTO getById(int id);

    public List<GrupoDTO> getAll();

}
