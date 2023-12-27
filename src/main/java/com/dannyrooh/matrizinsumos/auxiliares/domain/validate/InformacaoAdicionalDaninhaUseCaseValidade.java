package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;

public interface InformacaoAdicionalDaninhaUseCaseValidade {

    public void validateInsert(InformacaoAdicionalDaninhaDTO grupoDTO);

    public void validateUpdate(InformacaoAdicionalDaninhaDTO grupoDTO);

    public void validateDelete(Integer id);

}
