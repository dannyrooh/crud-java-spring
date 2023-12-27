package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.EpocaAplicacao;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.EpocaAplicacaoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.EpocaAplicacaoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.EpocaAplicacaoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.EpocaAplicacaoUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.EpocaAplicacaoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class EpocaAplicacaoUseCaseImpl implements EpocaAplicacaoUseCase {

    private final EpocaAplicacaoRepository grupoRepository;
    private final EpocaAplicacaoMapper grupoMapper;
    private final EpocaAplicacaoUseCaseValidateImpl grupoUseCaseValidate;

    public EpocaAplicacaoUseCaseImpl(EpocaAplicacaoRepository grupoRepository,
            EpocaAplicacaoUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(EpocaAplicacaoMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public EpocaAplicacaoDTO insert(EpocaAplicacaoDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        EpocaAplicacao grupo = grupoMapper.grupoDTOToEpocaAplicacao(grupoDTO);
        EpocaAplicacao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToEpocaAplicacaoDTO(responde);
    }

    @Override
    public EpocaAplicacaoDTO update(EpocaAplicacaoDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        EpocaAplicacao grupo = grupoMapper.grupoDTOToEpocaAplicacao(grupoDTO);
        EpocaAplicacao responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToEpocaAplicacaoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<EpocaAplicacao> deletedEpocaAplicacao = this.grupoRepository.findById(id);
        return deletedEpocaAplicacao.isEmpty();
    }

    @Override
    public EpocaAplicacaoDTO getById(int id) {

        Optional<EpocaAplicacao> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToEpocaAplicacaoDTO(grupo.get());
    }

    @Override
    public List<EpocaAplicacaoDTO> getAll() {
        List<EpocaAplicacao> grupos = this.grupoRepository.findAll();
        return grupoMapper.listEpocaAplicacaoToListEpocaAplicacaoDTO(grupos);
    }

}
