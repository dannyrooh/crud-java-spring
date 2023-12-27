package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;

public interface EpocaAplicacaoUseCaseValidade {

    public void validateInsert(EpocaAplicacaoDTO grupoDTO);

    public void validateUpdate(EpocaAplicacaoDTO grupoDTO);

    public void validateDelete(Integer id);

}
