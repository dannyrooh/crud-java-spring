package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;

public interface TipoPersistenciaUseCaseValidade {

    public void validateInsert(TipoPersistenciaDTO grupoDTO);

    public void validateUpdate(TipoPersistenciaDTO grupoDTO);

    public void validateDelete(Integer id);

}
