package com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.InformacaoAdicionalMatrizUseCaseValidade;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

@Component
public class InformacaoAdicionalMatrizUseCaseValidateImpl implements InformacaoAdicionalMatrizUseCaseValidade {

    private void validateName(InformacaoAdicionalMatrizDTO grupoDTO, Integer size) {
        String name = grupoDTO.getNome();
        if ((grupoDTO == null) || (StringUtils.isBlank(name))) {
            throw new WithNameEmptyException();
        }

        if (name.length() > size) {
            throw new WithNameMaxSizeException();
        }
    }

    public void validateInsert(InformacaoAdicionalMatrizDTO grupoDTO) {
        validateName(grupoDTO, 50);
    }

    public void validateUpdate(InformacaoAdicionalMatrizDTO grupoDTO) {

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