package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GrupoDTO;

public interface GrupoUseCaseValidade {

    public void validateInsert(GrupoDTO grupoDTO);

    public void validateUpdate(GrupoDTO grupoDTO);

    public void validateDelete(Integer id);

}
