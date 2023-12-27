package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalMatriz;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InformacaoAdicionalMatrizMapper {

    InformacaoAdicionalMatriz grupoDTOToInformacaoAdicionalMatriz(InformacaoAdicionalMatrizDTO grupoDto);

    InformacaoAdicionalMatrizDTO grupoToInformacaoAdicionalMatrizDTO(InformacaoAdicionalMatriz grupo);

    List<InformacaoAdicionalMatriz> listInformacaoAdicionalMatrizDTOToListInformacaoAdicionalMatriz(List<InformacaoAdicionalMatrizDTO> grupoDTOList);

    List<InformacaoAdicionalMatrizDTO> listInformacaoAdicionalMatrizToListInformacaoAdicionalMatrizDTO(List<InformacaoAdicionalMatriz> grupoList);

}
