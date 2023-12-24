package com.dannyrooh.matrizinsumos.grupo.domain.validate;

import org.apache.commons.lang3.StringUtils;

import com.dannyrooh.matrizinsumos.exception.WithIdZeroOrNotInformedException;
import com.dannyrooh.matrizinsumos.exception.WithNameEmptyException;
import com.dannyrooh.matrizinsumos.exception.WithNameMaxSizeException;
import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;

public class GrupoUseCaseValidate {

    private static void validateName(GrupoDTO grupoDTO, Integer size) {
        String name = grupoDTO.getNome();
        if (StringUtils.isBlank(name)) {
            throw new WithNameEmptyException();
        }

        if (name.length() > size) {
            throw new WithNameMaxSizeException();
        }
    }

    public static void validateInsert(GrupoDTO grupoDTO) {

        if (grupoDTO == null)
            throw new NullPointerException();

        validateName(grupoDTO, 50);
    }

    public static void validateUpdate(GrupoDTO grupoDTO) {

        if (grupoDTO == null)
            throw new NullPointerException();

        Integer id = grupoDTO.getId();

        if (id == null || id <= 0) {
            throw new WithIdZeroOrNotInformedException();
        }

        validateName(grupoDTO, 50);
    }

}
