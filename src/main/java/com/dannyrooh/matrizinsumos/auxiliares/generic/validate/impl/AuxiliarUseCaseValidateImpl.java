package com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl;

import org.apache.commons.lang3.StringUtils;

import com.dannyrooh.matrizinsumos.auxiliares.generic.dto.AuxiliarDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.AuxiliarUseCaseValidate;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

public class AuxiliarUseCaseValidateImpl<D extends AuxiliarDTO, ID> implements AuxiliarUseCaseValidate<D, ID> {

    private void validateName(D dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O DTO não pode ser nulo");
        }

        String nome = dto.getNome();

        if (StringUtils.isBlank(nome)) {
            throw new WithNameEmptyException();
        }

        if (nome.length() > dto.getNameSize()) {
            throw new WithNameMaxSizeException();
        }

    }

    private void validateID(D dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O DTO não pode ser nulo");
        }

        Integer id = dto.getId();

        if (id == null || id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }

    }

    @Override
    public void validateInsert(D objetoDTO) {
        validateName(objetoDTO);
    }

    @Override
    public void validateUpdate(D objetoDTO) {

        validateID(objetoDTO);
        validateName(objetoDTO);
    }

    @Override
    public void validateDelete(ID id) {
        if (id == null || (Integer) id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }
    }

}
