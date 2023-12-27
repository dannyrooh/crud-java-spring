package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;

public interface InformacaoAdicionalMatrizUseCaseValidade {

    public void validateInsert(InformacaoAdicionalMatrizDTO grupoDTO);

    public void validateUpdate(InformacaoAdicionalMatrizDTO grupoDTO);

    public void validateDelete(Integer id);

}
