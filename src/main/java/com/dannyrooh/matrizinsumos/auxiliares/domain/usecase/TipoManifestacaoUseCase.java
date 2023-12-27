package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase;

import java.util.List;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;

public interface TipoManifestacaoUseCase {

    public TipoManifestacaoDTO insert(TipoManifestacaoDTO grupo);

    public TipoManifestacaoDTO update(TipoManifestacaoDTO grupo);

    public Boolean delete(int id);

    public TipoManifestacaoDTO getById(int id);

    public List<TipoManifestacaoDTO> getAll();

}
