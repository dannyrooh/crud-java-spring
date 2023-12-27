package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.EpocaAplicacao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EpocaAplicacaoMapper {

    EpocaAplicacao grupoDTOToEpocaAplicacao(EpocaAplicacaoDTO grupoDto);

    EpocaAplicacaoDTO grupoToEpocaAplicacaoDTO(EpocaAplicacao grupo);

    List<EpocaAplicacao> listEpocaAplicacaoDTOToListEpocaAplicacao(List<EpocaAplicacaoDTO> grupoDTOList);

    List<EpocaAplicacaoDTO> listEpocaAplicacaoToListEpocaAplicacaoDTO(List<EpocaAplicacao> grupoList);

}
