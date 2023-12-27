package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoManifestacao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoManifestacaoMapper {

    TipoManifestacao grupoDTOToTipoManifestacao(TipoManifestacaoDTO grupoDto);

    TipoManifestacaoDTO grupoToTipoManifestacaoDTO(TipoManifestacao grupo);

    List<TipoManifestacao> listTipoManifestacaoDTOToListTipoManifestacao(List<TipoManifestacaoDTO> grupoDTOList);

    List<TipoManifestacaoDTO> listTipoManifestacaoToListTipoManifestacaoDTO(List<TipoManifestacao> grupoList);

}
