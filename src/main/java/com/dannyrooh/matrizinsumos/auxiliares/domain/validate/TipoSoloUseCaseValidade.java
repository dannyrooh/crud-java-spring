package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;

public interface TipoSoloUseCaseValidade {

    public void validateInsert(TipoSoloDTO grupoDTO);

    public void validateUpdate(TipoSoloDTO grupoDTO);

    public void validateDelete(Integer id);

}
