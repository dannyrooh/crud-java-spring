package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InformacaoAdicionalDaninhaMapper {

    InformacaoAdicionalDaninha grupoDTOToInformacaoAdicionalDaninha(InformacaoAdicionalDaninhaDTO grupoDto);

    InformacaoAdicionalDaninhaDTO grupoToInformacaoAdicionalDaninhaDTO(InformacaoAdicionalDaninha grupo);

    List<InformacaoAdicionalDaninha> listInformacaoAdicionalDaninhaDTOToListInformacaoAdicionalDaninha(List<InformacaoAdicionalDaninhaDTO> grupoDTOList);

    List<InformacaoAdicionalDaninhaDTO> listInformacaoAdicionalDaninhaToListInformacaoAdicionalDaninhaDTO(List<InformacaoAdicionalDaninha> grupoList);

}
