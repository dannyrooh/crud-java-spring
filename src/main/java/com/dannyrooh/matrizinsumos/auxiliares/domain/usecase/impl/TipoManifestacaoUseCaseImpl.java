package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.TipoManifestacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.TipoManifestacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.TipoManifestacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.TipoManifestacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.TipoManifestacaoUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.TipoManifestacaoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class TipoManifestacaoUseCaseImpl implements TipoManifestacaoUseCase {

    private final TipoManifestacaoRepository grupoRepository;
    private final TipoManifestacaoMapper grupoMapper;
    private final TipoManifestacaoUseCaseValidateImpl grupoUseCaseValidate;

    public TipoManifestacaoUseCaseImpl(TipoManifestacaoRepository grupoRepository,
            TipoManifestacaoUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(TipoManifestacaoMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public TipoManifestacaoDTO insert(TipoManifestacaoDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        TipoManifestacao grupo = grupoMapper.grupoDTOToTipoManifestacao(grupoDTO);
        TipoManifestacao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoManifestacaoDTO(responde);
    }

    @Override
    public TipoManifestacaoDTO update(TipoManifestacaoDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        TipoManifestacao grupo = grupoMapper.grupoDTOToTipoManifestacao(grupoDTO);
        TipoManifestacao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToTipoManifestacaoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<TipoManifestacao> deletedTipoManifestacao = this.grupoRepository.findById(id);
        return deletedTipoManifestacao.isEmpty();
    }

    @Override
    public TipoManifestacaoDTO getById(int id) {

        Optional<TipoManifestacao> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToTipoManifestacaoDTO(grupo.get());
    }

    @Override
    public List<TipoManifestacaoDTO> getAll() {
        List<TipoManifestacao> grupos = this.grupoRepository.findAll();
        return grupoMapper.listTipoManifestacaoToListTipoManifestacaoDTO(grupos);
    }

}
