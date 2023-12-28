package com.dannyrooh.matrizinsumos.auxiliares.domain.mapper;

import org.mapstruct.Mapper;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ModoAcao;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ModoAcaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;

@Mapper(componentModel = "spring")
public interface ModoAcaoMapper extends AuxiliarMapper<ModoAcao, ModoAcaoDTO> {
}
