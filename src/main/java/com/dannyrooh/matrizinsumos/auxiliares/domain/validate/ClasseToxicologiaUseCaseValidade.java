package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;

public interface ClasseToxicologiaUseCaseValidade {

    public void validateInsert(ClasseToxicologiaDTO grupoDTO);

    public void validateUpdate(ClasseToxicologiaDTO grupoDTO);

    public void validateDelete(Integer id);

}
