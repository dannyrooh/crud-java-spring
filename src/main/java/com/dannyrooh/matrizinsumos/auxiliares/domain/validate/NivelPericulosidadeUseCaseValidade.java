package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;

public interface NivelPericulosidadeUseCaseValidade {

    public void validateInsert(NivelPericulosidadeDTO grupoDTO);

    public void validateUpdate(NivelPericulosidadeDTO grupoDTO);

    public void validateDelete(Integer id);

}
