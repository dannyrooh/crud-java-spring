package com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.AtuacaoUseCaseValidade;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

@Component
public class AtuacaoUseCaseValidateImpl implements AtuacaoUseCaseValidade {

    private void validateName(AtuacaoDTO atuacaoDTO, Integer size) {
        String name = atuacaoDTO.getNome();
        if ((atuacaoDTO == null) || (StringUtils.isBlank(name))) {
            throw new WithNameEmptyException();
        }

        if (name.length() > size) {
            throw new WithNameMaxSizeException();
        }
    }

    public void validateInsert(AtuacaoDTO atuacaoDTO) {
        validateName(atuacaoDTO, 50);
    }

    public void validateUpdate(AtuacaoDTO atuacaoDTO) {

        if (atuacaoDTO == null)
            throw new NullPointerException();

        Integer id = atuacaoDTO.getId();

        if (id == null || id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }

        validateName(atuacaoDTO, 50);
    }

    public void validateDelete(Integer id) {

        if (id == null)
            throw new NullPointerException();

        if (id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }

    }

}
