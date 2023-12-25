package com.dannyrooh.matrizinsumos.grupo.domain.validate;

import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;

public interface GrupoUseCaseValidade {

    public void validateInsert(GrupoDTO grupoDTO);

    public void validateUpdate(GrupoDTO grupoDTO);

    public void validateDelete(Integer id);

}
