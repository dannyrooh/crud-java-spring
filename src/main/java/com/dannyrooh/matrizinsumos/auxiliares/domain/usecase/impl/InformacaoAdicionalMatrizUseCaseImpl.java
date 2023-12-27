package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.InformacaoAdicionalMatriz;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.InformacaoAdicionalMatrizRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.InformacaoAdicionalMatrizDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.InformacaoAdicionalMatrizMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.InformacaoAdicionalMatrizUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.InformacaoAdicionalMatrizUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class InformacaoAdicionalMatrizUseCaseImpl implements InformacaoAdicionalMatrizUseCase {

    private final InformacaoAdicionalMatrizRepository grupoRepository;
    private final InformacaoAdicionalMatrizMapper grupoMapper;
    private final InformacaoAdicionalMatrizUseCaseValidateImpl grupoUseCaseValidate;

    public InformacaoAdicionalMatrizUseCaseImpl(InformacaoAdicionalMatrizRepository grupoRepository,
            InformacaoAdicionalMatrizUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(InformacaoAdicionalMatrizMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public InformacaoAdicionalMatrizDTO insert(InformacaoAdicionalMatrizDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        InformacaoAdicionalMatriz grupo = grupoMapper.grupoDTOToInformacaoAdicionalMatriz(grupoDTO);
        InformacaoAdicionalMatriz responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToInformacaoAdicionalMatrizDTO(responde);
    }

    @Override
    public InformacaoAdicionalMatrizDTO update(InformacaoAdicionalMatrizDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        InformacaoAdicionalMatriz grupo = grupoMapper.grupoDTOToInformacaoAdicionalMatriz(grupoDTO);
        InformacaoAdicionalMatriz responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToInformacaoAdicionalMatrizDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<InformacaoAdicionalMatriz> deletedInformacaoAdicionalMatriz = this.grupoRepository.findById(id);
        return deletedInformacaoAdicionalMatriz.isEmpty();
    }

    @Override
    public InformacaoAdicionalMatrizDTO getById(int id) {

        Optional<InformacaoAdicionalMatriz> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToInformacaoAdicionalMatrizDTO(grupo.get());
    }

    @Override
    public List<InformacaoAdicionalMatrizDTO> getAll() {
        List<InformacaoAdicionalMatriz> grupos = this.grupoRepository.findAll();
        return grupoMapper.listInformacaoAdicionalMatrizToListInformacaoAdicionalMatrizDTO(grupos);
    }

}
