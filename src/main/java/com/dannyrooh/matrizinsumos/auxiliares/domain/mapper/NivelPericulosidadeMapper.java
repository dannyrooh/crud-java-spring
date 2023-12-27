package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.NivelPericulosidade;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.NivelPericulosidadeDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NivelPericulosidadeMapper {

    NivelPericulosidade grupoDTOToNivelPericulosidade(NivelPericulosidadeDTO grupoDto);

    NivelPericulosidadeDTO grupoToNivelPericulosidadeDTO(NivelPericulosidade grupo);

    List<NivelPericulosidade> listNivelPericulosidadeDTOToListNivelPericulosidade(List<NivelPericulosidadeDTO> grupoDTOList);

    List<NivelPericulosidadeDTO> listNivelPericulosidadeToListNivelPericulosidadeDTO(List<NivelPericulosidade> grupoList);

}
