package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoSolo;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoSoloDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoSoloMapper {

    TipoSolo grupoDTOToTipoSolo(TipoSoloDTO grupoDto);

    TipoSoloDTO grupoToTipoSoloDTO(TipoSolo grupo);

    List<TipoSolo> listTipoSoloDTOToListTipoSolo(List<TipoSoloDTO> grupoDTOList);

    List<TipoSoloDTO> listTipoSoloToListTipoSoloDTO(List<TipoSolo> grupoList);

}
