package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.PlantaDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlantaDaninhaMapper {

    PlantaDaninha grupoDTOToPlantaDaninha(PlantaDaninhaDTO grupoDto);

    PlantaDaninhaDTO grupoToPlantaDaninhaDTO(PlantaDaninha grupo);

    List<PlantaDaninha> listPlantaDaninhaDTOToListPlantaDaninha(List<PlantaDaninhaDTO> grupoDTOList);

    List<PlantaDaninhaDTO> listPlantaDaninhaToListPlantaDaninhaDTO(List<PlantaDaninha> grupoList);

}
