package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Genero;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.GeneroRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.GeneroMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class GeneroUseCaseImpl extends
        AuxiliarUseCaseImpl<GeneroDTO, Genero, Integer, GeneroRepository, AuxiliarMapper<Genero, GeneroDTO>, AuxiliarUseCaseValidateImpl<GeneroDTO, Integer>> {

    public GeneroUseCaseImpl(GeneroRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(GeneroMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<GeneroDTO, Integer>());
    }

}
