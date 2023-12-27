package com.dannyrooh.matrizinsumos.auxiliares.domain.validate;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;

public interface ClasseProdutoUseCaseValidade {

    public void validateInsert(ClasseProdutoDTO grupoDTO);

    public void validateUpdate(ClasseProdutoDTO grupoDTO);

    public void validateDelete(Integer id);

}
