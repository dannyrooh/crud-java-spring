package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Grupo;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GrupoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    Grupo grupoDTOToGrupo(GrupoDTO grupoDto);

    GrupoDTO grupoToGrupoDTO(Grupo grupo);

    List<Grupo> listGrupoDTOToListGrupo(List<GrupoDTO> grupoDTOList);

    List<GrupoDTO> listGrupoToListGrupoDTO(List<Grupo> grupoList);

}
