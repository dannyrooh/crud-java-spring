package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalMatriz;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalMatrizRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.InformacaoAdicionalMatrizMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class InformacaoAdicionalMatrizUseCaseImpl extends
        AuxiliarUseCaseImpl<InformacaoAdicionalMatrizDTO, InformacaoAdicionalMatriz, Integer, InformacaoAdicionalMatrizRepository, AuxiliarMapper<InformacaoAdicionalMatriz, InformacaoAdicionalMatrizDTO>, AuxiliarUseCaseValidateImpl<InformacaoAdicionalMatrizDTO, Integer>> {

    public InformacaoAdicionalMatrizUseCaseImpl(InformacaoAdicionalMatrizRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(InformacaoAdicionalMatrizMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<InformacaoAdicionalMatrizDTO, Integer>());
    }

}
