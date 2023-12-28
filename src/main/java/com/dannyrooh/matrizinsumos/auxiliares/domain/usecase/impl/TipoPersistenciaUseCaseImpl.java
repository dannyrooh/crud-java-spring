package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoPersistencia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoPersistenciaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoPersistenciaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class TipoPersistenciaUseCaseImpl extends
        AuxiliarUseCaseImpl<TipoPersistenciaDTO, TipoPersistencia, Integer, TipoPersistenciaRepository, AuxiliarMapper<TipoPersistencia, TipoPersistenciaDTO>, AuxiliarUseCaseValidateImpl<TipoPersistenciaDTO, Integer>> {

    public TipoPersistenciaUseCaseImpl(TipoPersistenciaRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(TipoPersistenciaMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<TipoPersistenciaDTO, Integer>());
    }

}
