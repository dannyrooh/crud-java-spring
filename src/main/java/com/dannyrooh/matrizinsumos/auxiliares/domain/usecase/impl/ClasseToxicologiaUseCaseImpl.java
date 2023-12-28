package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseToxicologia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseToxicologiaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.ClasseToxicologiaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class ClasseToxicologiaUseCaseImpl extends
        AuxiliarUseCaseImpl<ClasseToxicologiaDTO, ClasseToxicologia, Integer, ClasseToxicologiaRepository, AuxiliarMapper<ClasseToxicologia, ClasseToxicologiaDTO>, AuxiliarUseCaseValidateImpl<ClasseToxicologiaDTO, Integer>> {

    public ClasseToxicologiaUseCaseImpl(ClasseToxicologiaRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(ClasseToxicologiaMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<ClasseToxicologiaDTO, Integer>());
    }

}
