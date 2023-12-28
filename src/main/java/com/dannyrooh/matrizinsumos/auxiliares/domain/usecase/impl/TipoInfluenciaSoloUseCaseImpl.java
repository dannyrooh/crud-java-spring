package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoInfluenciaSolo;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoInfluenciaSoloRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoInfluenciaSoloMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class TipoInfluenciaSoloUseCaseImpl extends
        AuxiliarUseCaseImpl<TipoInfluenciaSoloDTO, TipoInfluenciaSolo, Integer, TipoInfluenciaSoloRepository, AuxiliarMapper<TipoInfluenciaSolo, TipoInfluenciaSoloDTO>, AuxiliarUseCaseValidateImpl<TipoInfluenciaSoloDTO, Integer>> {

    public TipoInfluenciaSoloUseCaseImpl(TipoInfluenciaSoloRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(TipoInfluenciaSoloMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<TipoInfluenciaSoloDTO, Integer>());
    }

}
