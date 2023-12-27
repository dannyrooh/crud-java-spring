package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.InformacaoAdicionalDaninhaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.InformacaoAdicionalDaninhaUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.InformacaoAdicionalDaninhaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class InformacaoAdicionalDaninhaUseCaseImpl implements InformacaoAdicionalDaninhaUseCase {

    private final InformacaoAdicionalDaninhaRepository grupoRepository;
    private final InformacaoAdicionalDaninhaMapper grupoMapper;
    private final InformacaoAdicionalDaninhaUseCaseValidateImpl grupoUseCaseValidate;

    public InformacaoAdicionalDaninhaUseCaseImpl(InformacaoAdicionalDaninhaRepository grupoRepository,
            InformacaoAdicionalDaninhaUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(InformacaoAdicionalDaninhaMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public InformacaoAdicionalDaninhaDTO insert(InformacaoAdicionalDaninhaDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        InformacaoAdicionalDaninha grupo = grupoMapper.grupoDTOToInformacaoAdicionalDaninha(grupoDTO);
        InformacaoAdicionalDaninha responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToInformacaoAdicionalDaninhaDTO(responde);
    }

    @Override
    public InformacaoAdicionalDaninhaDTO update(InformacaoAdicionalDaninhaDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        InformacaoAdicionalDaninha grupo = grupoMapper.grupoDTOToInformacaoAdicionalDaninha(grupoDTO);
        InformacaoAdicionalDaninha responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToInformacaoAdicionalDaninhaDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<InformacaoAdicionalDaninha> deletedInformacaoAdicionalDaninha = this.grupoRepository.findById(id);
        return deletedInformacaoAdicionalDaninha.isEmpty();
    }

    @Override
    public InformacaoAdicionalDaninhaDTO getById(int id) {

        Optional<InformacaoAdicionalDaninha> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToInformacaoAdicionalDaninhaDTO(grupo.get());
    }

    @Override
    public List<InformacaoAdicionalDaninhaDTO> getAll() {
        List<InformacaoAdicionalDaninha> grupos = this.grupoRepository.findAll();
        return grupoMapper.listInformacaoAdicionalDaninhaToListInformacaoAdicionalDaninhaDTO(grupos);
    }

}
