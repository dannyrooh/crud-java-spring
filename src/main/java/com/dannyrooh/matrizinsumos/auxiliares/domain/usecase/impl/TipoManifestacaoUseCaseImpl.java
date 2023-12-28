package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoManifestacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoManifestacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoManifestacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.mapper.AuxiliarMapper;
import com.dannyrooh.matrizinsumos.auxiliares.generic.usecase.impl.AuxiliarUseCaseImpl;
import com.dannyrooh.matrizinsumos.auxiliares.generic.validate.impl.AuxiliarUseCaseValidateImpl;

@Service
public class TipoManifestacaoUseCaseImpl extends
        AuxiliarUseCaseImpl<TipoManifestacaoDTO, TipoManifestacao, Integer, TipoManifestacaoRepository, AuxiliarMapper<TipoManifestacao, TipoManifestacaoDTO>, AuxiliarUseCaseValidateImpl<TipoManifestacaoDTO, Integer>> {

    public TipoManifestacaoUseCaseImpl(TipoManifestacaoRepository repository) {
        setRepository(repository);
        setMapper(Mappers.getMapper(TipoManifestacaoMapper.class));
        setValidate(new AuxiliarUseCaseValidateImpl<TipoManifestacaoDTO, Integer>());
    }

}
