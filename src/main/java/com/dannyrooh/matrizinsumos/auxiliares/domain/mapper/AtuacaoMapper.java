package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Atuacao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AtuacaoMapper {

    Atuacao atuacaoDTOToAtuacao(AtuacaoDTO atuacaoDto);

    AtuacaoDTO atuacaoToAtuacaoDTO(Atuacao atuacao);

    List<Atuacao> listAtuacaoDTOToListAtuacao(List<AtuacaoDTO> atuacaoDTOList);

    List<AtuacaoDTO> listAtuacaoToListAtuacaoDTO(List<Atuacao> atuacaoList);

}
