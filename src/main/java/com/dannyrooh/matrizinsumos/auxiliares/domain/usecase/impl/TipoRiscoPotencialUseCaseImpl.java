package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoRiscoPotencial;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoRiscoPotencialRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoRiscoPotencialMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class TipoRiscoPotencialUseCaseImpl extends
        AuxiliarUseCaseImpl<TipoRiscoPotencialDTO, TipoRiscoPotencial, Integer, TipoRiscoPotencialRepository, AuxiliarMapper<TipoRiscoPotencial, TipoRiscoPotencialDTO>, AuxiliarUseCaseValidateImpl<TipoRiscoPotencialDTO, Integer>> {

    public TipoRiscoPotencialUseCaseImpl(TipoRiscoPotencialRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(TipoRiscoPotencialMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<TipoRiscoPotencialDTO, Integer>());
    }

}
