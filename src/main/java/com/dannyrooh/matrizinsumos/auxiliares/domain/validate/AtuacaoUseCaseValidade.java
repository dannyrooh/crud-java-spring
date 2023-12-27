package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;

public interface AtuacaoUseCaseValidade {

    public void validateInsert(AtuacaoDTO atuacaoDTO);

    public void validateUpdate(AtuacaoDTO atuacaoDTO);

    public void validateDelete(Integer id);

}
