package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;

public interface ModoAcaoUseCaseValidade {

    public void validateInsert(ModoAcaoDTO grupoDTO);

    public void validateUpdate(ModoAcaoDTO grupoDTO);

    public void validateDelete(Integer id);

}
