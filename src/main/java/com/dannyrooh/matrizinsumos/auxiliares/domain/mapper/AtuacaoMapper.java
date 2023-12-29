package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;



import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Atuacao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.AtuacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AtuacaoMapper extends AuxiliarMapper<Atuacao, AtuacaoDTO> {
}