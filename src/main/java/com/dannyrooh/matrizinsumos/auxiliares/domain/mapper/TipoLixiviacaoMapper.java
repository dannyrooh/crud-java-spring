package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoLixiviacao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoLixiviacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoLixiviacaoMapper {

    TipoLixiviacao grupoDTOToTipoLixiviacao(TipoLixiviacaoDTO grupoDto);

    TipoLixiviacaoDTO grupoToTipoLixiviacaoDTO(TipoLixiviacao grupo);

    List<TipoLixiviacao> listTipoLixiviacaoDTOToListTipoLixiviacao(List<TipoLixiviacaoDTO> grupoDTOList);

    List<TipoLixiviacaoDTO> listTipoLixiviacaoToListTipoLixiviacaoDTO(List<TipoLixiviacao> grupoList);

}
