package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;

public interface TipoRiscoPotencialUseCaseValidade {

    public void validateInsert(TipoRiscoPotencialDTO grupoDTO);

    public void validateUpdate(TipoRiscoPotencialDTO grupoDTO);

    public void validateDelete(Integer id);

}
