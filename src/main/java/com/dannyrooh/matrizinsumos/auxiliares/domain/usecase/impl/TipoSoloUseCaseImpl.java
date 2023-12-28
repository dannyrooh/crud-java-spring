package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoSoloMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class TipoSoloUseCaseImpl extends
        AuxiliarUseCaseImpl<TipoSoloDTO, TipoSolo, Integer, TipoSoloRepository, AuxiliarMapper<TipoSolo, TipoSoloDTO>, AuxiliarUseCaseValidateImpl<TipoSoloDTO, Integer>> {

    public TipoSoloUseCaseImpl(TipoSoloRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(TipoSoloMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<TipoSoloDTO, Integer>());
    }

}
