package com.dannyrooh.matrizinsumos.grupo.app.usecase.validator;

import java.util.Objects;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;

@Component
public class GrupoUseCaseValidator {

    public void insert(GrupoDTO grupoDTO) throws ValidationException {
        if (Objects.isNull(grupoDTO.getNome()) || grupoDTO.getNome().trim().isEmpty()) {
            throw new ValidationException("O campo 'nome' é obrigatório e não pode estar em branco.");
        }

    }

}
