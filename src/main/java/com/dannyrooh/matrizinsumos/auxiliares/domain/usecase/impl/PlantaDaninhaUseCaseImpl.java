package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.PlantaDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.PlantaDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.PlantaDaninhaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class PlantaDaninhaUseCaseImpl extends
        AuxiliarUseCaseImpl<PlantaDaninhaDTO, PlantaDaninha, Integer, PlantaDaninhaRepository, AuxiliarMapper<PlantaDaninha, PlantaDaninhaDTO>, AuxiliarUseCaseValidateImpl<PlantaDaninhaDTO, Integer>> {

    public PlantaDaninhaUseCaseImpl(PlantaDaninhaRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(PlantaDaninhaMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<PlantaDaninhaDTO, Integer>());
    }

}
