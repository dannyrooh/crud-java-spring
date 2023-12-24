package com.dannyrooh.matrizinsumos.grupo.domain.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.dannyrooh.matrizinsumos.grupo.dataprovider.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    Grupo grupoDTOToGrupo(GrupoDTO grupoDto);

    GrupoDTO grupoToGrupoDTO(Grupo grupo);

    List<Grupo> listGrupoDTOToListGrupo(List<GrupoDTO> grupoDTOList);

    List<GrupoDTO> listGrupoToListGrupoDTO(List<Grupo> grupoList);

}
