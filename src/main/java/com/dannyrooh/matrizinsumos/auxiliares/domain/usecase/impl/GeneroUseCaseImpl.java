package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.Genero;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.GeneroRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.GeneroDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.GeneroMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.GeneroUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.GeneroUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class GeneroUseCaseImpl implements GeneroUseCase {

    private final GeneroRepository grupoRepository;
    private final GeneroMapper grupoMapper;
    private final GeneroUseCaseValidateImpl grupoUseCaseValidate;

    public GeneroUseCaseImpl(GeneroRepository grupoRepository,
            GeneroUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(GeneroMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public GeneroDTO insert(GeneroDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        Genero grupo = grupoMapper.grupoDTOToGenero(grupoDTO);
        Genero responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGeneroDTO(responde);
    }

    @Override
    public GeneroDTO update(GeneroDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        Genero grupo = grupoMapper.grupoDTOToGenero(grupoDTO);
        Genero responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGeneroDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<Genero> deletedGenero = this.grupoRepository.findById(id);
        return deletedGenero.isEmpty();
    }

    @Override
    public GeneroDTO getById(int id) {

        Optional<Genero> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToGeneroDTO(grupo.get());
    }

    @Override
    public List<GeneroDTO> getAll() {
        List<Genero> grupos = this.grupoRepository.findAll();
        return grupoMapper.listGeneroToListGeneroDTO(grupos);
    }

}
