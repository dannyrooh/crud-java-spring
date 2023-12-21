package com.dannyrooh.matrizinsumos.grupo.app.mapper;

import org.mapstruct.Mapper;
import java.util.List;

import com.dannyrooh.matrizinsumos.grupo.app.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.domain.model.Grupo;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

    Grupo grupoDTOToGrupo(GrupoDTO grupoDto);

    GrupoDTO grupoToGrupoDTO(Grupo grupo);

    List<Grupo> listGrupoDTOToListGrupo(List<GrupoDTO> grupoDTOList);

    List<GrupoDTO> listGrupoToListGrupoDTO(List<Grupo> grupoList);

}
