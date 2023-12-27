package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoPersistencia;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoPersistenciaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoPersistenciaMapper {

    TipoPersistencia grupoDTOToTipoPersistencia(TipoPersistenciaDTO grupoDto);

    TipoPersistenciaDTO grupoToTipoPersistenciaDTO(TipoPersistencia grupo);

    List<TipoPersistencia> listTipoPersistenciaDTOToListTipoPersistencia(List<TipoPersistenciaDTO> grupoDTOList);

    List<TipoPersistenciaDTO> listTipoPersistenciaToListTipoPersistenciaDTO(List<TipoPersistencia> grupoList);

}
