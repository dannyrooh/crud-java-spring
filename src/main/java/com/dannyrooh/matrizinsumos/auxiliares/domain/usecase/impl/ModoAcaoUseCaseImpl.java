package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ModoAcao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ModoAcaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.ModoAcaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class ModoAcaoUseCaseImpl extends
        AuxiliarUseCaseImpl<ModoAcaoDTO, ModoAcao, Integer, ModoAcaoRepository, AuxiliarMapper<ModoAcao, ModoAcaoDTO>, AuxiliarUseCaseValidateImpl<ModoAcaoDTO, Integer>> {

    public ModoAcaoUseCaseImpl(ModoAcaoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(ModoAcaoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<ModoAcaoDTO, Integer>());
    }

}
