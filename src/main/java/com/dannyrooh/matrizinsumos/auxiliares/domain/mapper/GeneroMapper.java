package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Genero;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    Genero grupoDTOToGenero(GeneroDTO grupoDto);

    GeneroDTO grupoToGeneroDTO(Genero grupo);

    List<Genero> listGeneroDTOToListGenero(List<GeneroDTO> grupoDTOList);

    List<GeneroDTO> listGeneroToListGeneroDTO(List<Genero> grupoList);

}
