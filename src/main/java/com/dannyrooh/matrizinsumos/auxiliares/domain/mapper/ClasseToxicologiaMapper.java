package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseToxicologia;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClasseToxicologiaMapper {

    ClasseToxicologia grupoDTOToClasseToxicologia(ClasseToxicologiaDTO grupoDto);

    ClasseToxicologiaDTO grupoToClasseToxicologiaDTO(ClasseToxicologia grupo);

    List<ClasseToxicologia> listClasseToxicologiaDTOToListClasseToxicologia(List<ClasseToxicologiaDTO> grupoDTOList);

    List<ClasseToxicologiaDTO> listClasseToxicologiaToListClasseToxicologiaDTO(List<ClasseToxicologia> grupoList);

}
