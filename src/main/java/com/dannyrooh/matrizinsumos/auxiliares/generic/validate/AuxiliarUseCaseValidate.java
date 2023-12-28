package com.dannyrooh.matrizinsumos.auxiliares.generic.validate;

import com.dannyrooh.matrizinsumos.auxiliares.generic.dto.AuxiliarDTO;

public interface AuxiliarUseCaseValidate<D extends AuxiliarDTO, ID> {

    public void validateInsert(D dto);

    public void validateUpdate(D dto);

    public void validateDelete(ID id);

}
