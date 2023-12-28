package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.PlantaDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;

@Mapper(componentModel = "spring")
public interface PlantaDaninhaMapper extends AuxiliarMapper<PlantaDaninha, PlantaDaninhaDTO> {
}
