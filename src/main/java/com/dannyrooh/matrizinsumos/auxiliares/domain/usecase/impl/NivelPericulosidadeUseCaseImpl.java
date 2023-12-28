package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.NivelPericulosidade;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.NivelPericulosidadeRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.NivelPericulosidadeMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class NivelPericulosidadeUseCaseImpl extends
        AuxiliarUseCaseImpl<NivelPericulosidadeDTO, NivelPericulosidade, Integer, NivelPericulosidadeRepository, AuxiliarMapper<NivelPericulosidade, NivelPericulosidadeDTO>, AuxiliarUseCaseValidateImpl<NivelPericulosidadeDTO, Integer>> {

    public NivelPericulosidadeUseCaseImpl(NivelPericulosidadeRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(NivelPericulosidadeMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<NivelPericulosidadeDTO, Integer>());
    }

}
