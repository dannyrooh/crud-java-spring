package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.EpocaAplicacao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;

@Mapper(componentModel = "spring")
public interface EpocaAplicacaoMapper extends AuxiliarMapper<EpocaAplicacao, EpocaAplicacaoDTO> {
}
