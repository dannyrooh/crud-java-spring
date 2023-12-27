package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoRiscoPotencial;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoRiscoPotencialDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoRiscoPotencialMapper {

    TipoRiscoPotencial grupoDTOToTipoRiscoPotencial(TipoRiscoPotencialDTO grupoDto);

    TipoRiscoPotencialDTO grupoToTipoRiscoPotencialDTO(TipoRiscoPotencial grupo);

    List<TipoRiscoPotencial> listTipoRiscoPotencialDTOToListTipoRiscoPotencial(List<TipoRiscoPotencialDTO> grupoDTOList);

    List<TipoRiscoPotencialDTO> listTipoRiscoPotencialToListTipoRiscoPotencialDTO(List<TipoRiscoPotencial> grupoList);

}
