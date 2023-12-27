package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;

public interface TipoManifestacaoUseCaseValidade {

    public void validateInsert(TipoManifestacaoDTO grupoDTO);

    public void validateUpdate(TipoManifestacaoDTO grupoDTO);

    public void validateDelete(Integer id);

}
