package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;

public interface InformacaoAdicionalDaninhaUseCase {

    public InformacaoAdicionalDaninhaDTO insert(InformacaoAdicionalDaninhaDTO grupo);

    public InformacaoAdicionalDaninhaDTO update(InformacaoAdicionalDaninhaDTO grupo);

    public Boolean delete(int id);

    public InformacaoAdicionalDaninhaDTO getById(int id);

    public List<InformacaoAdicionalDaninhaDTO> getAll();

}
