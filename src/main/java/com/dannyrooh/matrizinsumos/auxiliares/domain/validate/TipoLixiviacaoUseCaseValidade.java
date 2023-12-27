package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;

public interface TipoLixiviacaoUseCaseValidade {

    public void validateInsert(TipoLixiviacaoDTO grupoDTO);

    public void validateUpdate(TipoLixiviacaoDTO grupoDTO);

    public void validateDelete(Integer id);

}
