package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;

public interface TipoInfluenciaSoloUseCaseValidade {

    public void validateInsert(TipoInfluenciaSoloDTO grupoDTO);

    public void validateUpdate(TipoInfluenciaSoloDTO grupoDTO);

    public void validateDelete(Integer id);

}
