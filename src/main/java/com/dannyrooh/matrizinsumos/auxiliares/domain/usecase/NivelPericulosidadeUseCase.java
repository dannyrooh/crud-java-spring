package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;

public interface NivelPericulosidadeUseCase {

    public NivelPericulosidadeDTO insert(NivelPericulosidadeDTO grupo);

    public NivelPericulosidadeDTO update(NivelPericulosidadeDTO grupo);

    public Boolean delete(int id);

    public NivelPericulosidadeDTO getById(int id);

    public List<NivelPericulosidadeDTO> getAll();

}
