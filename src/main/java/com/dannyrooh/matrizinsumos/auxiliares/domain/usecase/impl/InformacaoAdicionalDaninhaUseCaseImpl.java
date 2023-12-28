package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.InformacaoAdicionalDaninhaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class InformacaoAdicionalDaninhaUseCaseImpl extends
        AuxiliarUseCaseImpl<InformacaoAdicionalDaninhaDTO, InformacaoAdicionalDaninha, Integer, InformacaoAdicionalDaninhaRepository, AuxiliarMapper<InformacaoAdicionalDaninha, InformacaoAdicionalDaninhaDTO>, AuxiliarUseCaseValidateImpl<InformacaoAdicionalDaninhaDTO, Integer>> {

    public InformacaoAdicionalDaninhaUseCaseImpl(InformacaoAdicionalDaninhaRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(InformacaoAdicionalDaninhaMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<InformacaoAdicionalDaninhaDTO, Integer>());
    }

}
