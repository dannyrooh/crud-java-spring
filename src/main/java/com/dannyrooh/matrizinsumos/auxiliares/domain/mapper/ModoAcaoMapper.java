package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ModoAcao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModoAcaoMapper {

    ModoAcao grupoDTOToModoAcao(ModoAcaoDTO grupoDto);

    ModoAcaoDTO grupoToModoAcaoDTO(ModoAcao grupo);

    List<ModoAcao> listModoAcaoDTOToListModoAcao(List<ModoAcaoDTO> grupoDTOList);

    List<ModoAcaoDTO> listModoAcaoToListModoAcaoDTO(List<ModoAcao> grupoList);

}
