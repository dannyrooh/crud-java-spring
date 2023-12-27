package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoInfluenciaSolo;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoInfluenciaSoloDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoInfluenciaSoloMapper {

    TipoInfluenciaSolo grupoDTOToTipoInfluenciaSolo(TipoInfluenciaSoloDTO grupoDto);

    TipoInfluenciaSoloDTO grupoToTipoInfluenciaSoloDTO(TipoInfluenciaSolo grupo);

    List<TipoInfluenciaSolo> listTipoInfluenciaSoloDTOToListTipoInfluenciaSolo(List<TipoInfluenciaSoloDTO> grupoDTOList);

    List<TipoInfluenciaSoloDTO> listTipoInfluenciaSoloToListTipoInfluenciaSoloDTO(List<TipoInfluenciaSolo> grupoList);

}
