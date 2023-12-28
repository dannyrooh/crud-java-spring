package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoLixiviacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoLixiviacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoLixiviacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class TipoLixiviacaoUseCaseImpl extends
        AuxiliarUseCaseImpl<TipoLixiviacaoDTO, TipoLixiviacao, Integer, TipoLixiviacaoRepository, AuxiliarMapper<TipoLixiviacao, TipoLixiviacaoDTO>, AuxiliarUseCaseValidateImpl<TipoLixiviacaoDTO, Integer>> {

    public TipoLixiviacaoUseCaseImpl(TipoLixiviacaoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(TipoLixiviacaoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<TipoLixiviacaoDTO, Integer>());
    }

}
