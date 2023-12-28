package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.EpocaAplicacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.EpocaAplicacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.EpocaAplicacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class EpocaAplicacaoUseCaseImpl extends
        AuxiliarUseCaseImpl<EpocaAplicacaoDTO, EpocaAplicacao, Integer, EpocaAplicacaoRepository, AuxiliarMapper<EpocaAplicacao, EpocaAplicacaoDTO>, AuxiliarUseCaseValidateImpl<EpocaAplicacaoDTO, Integer>> {

    public EpocaAplicacaoUseCaseImpl(EpocaAplicacaoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(EpocaAplicacaoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<EpocaAplicacaoDTO, Integer>());
    }

}
