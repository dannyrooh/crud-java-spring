package com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.ClasseProdutoUseCaseValidade;
import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;

@Component
public class ClasseProdutoUseCaseValidateImpl implements ClasseProdutoUseCaseValidade {

    private void validateName(ClasseProdutoDTO grupoDTO, Integer size) {
        String name = grupoDTO.getNome();
        if ((grupoDTO == null) || (StringUtils.isBlank(name))) {
            throw new WithNameEmptyException();
        }

        if (name.length() > size) {
            throw new WithNameMaxSizeException();
        }
    }

    public void validateInsert(ClasseProdutoDTO grupoDTO) {
        validateName(grupoDTO, 50);
    }

    public void validateUpdate(ClasseProdutoDTO grupoDTO) {

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
