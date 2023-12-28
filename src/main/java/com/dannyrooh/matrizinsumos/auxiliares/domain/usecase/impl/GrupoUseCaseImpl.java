package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Grupo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.GrupoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.GrupoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class GrupoUseCaseImpl extends
        AuxiliarUseCaseImpl<GrupoDTO, Grupo, Integer, GrupoRepository, AuxiliarMapper<Grupo, GrupoDTO>, AuxiliarUseCaseValidateImpl<GrupoDTO, Integer>> {

    public GrupoUseCaseImpl(GrupoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(GrupoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<GrupoDTO, Integer>());
    }

}
