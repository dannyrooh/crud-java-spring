package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;

public interface GeneroUseCaseValidade {

    public void validateInsert(GeneroDTO grupoDTO);

    public void validateUpdate(GeneroDTO grupoDTO);

    public void validateDelete(Integer id);

}
