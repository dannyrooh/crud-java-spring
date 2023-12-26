package com.dannyrooh.matrizinsumos.grupo.domain.validate.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;
import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.domain.validate.GrupoUseCaseValidade;

@Component
public class GrupoUseCaseValidateImpl implements GrupoUseCaseValidade {

    private void validateName(GrupoDTO grupoDTO, Integer size) {
        String name = grupoDTO.getNome();
        if ((grupoDTO == null) || (StringUtils.isBlank(name))) {
            throw new WithNameEmptyException();
        }

        if (name.length() > size) {
            throw new WithNameMaxSizeException();
        }
    }

    public void validateInsert(GrupoDTO grupoDTO) {
        validateName(grupoDTO, 50);
    }

    public void validateUpdate(GrupoDTO grupoDTO) {

        if (grupoDTO == null)
            throw new NullPointerException();

        Integer id = grupoDTO.getId();

        if (id == null || id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }

        validateName(grupoDTO, 50);
    }

    public void validateDelete(Integer id) {

        if (id == null)
            throw new NullPointerException();

        if (id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }

    }

}