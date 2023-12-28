package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Atuacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.AtuacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.AtuacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class AtuacaoUseCaseImpl extends
        AuxiliarUseCaseImpl<AtuacaoDTO, Atuacao, Integer, AtuacaoRepository, AuxiliarMapper<Atuacao, AtuacaoDTO>, AuxiliarUseCaseValidateImpl<AtuacaoDTO, Integer>> {

    public AtuacaoUseCaseImpl(AtuacaoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(AtuacaoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<AtuacaoDTO, Integer>());
    }

}
