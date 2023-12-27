package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseToxicologia;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseToxicologiaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseToxicologiaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.ClasseToxicologiaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.ClasseToxicologiaUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.ClasseToxicologiaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class ClasseToxicologiaUseCaseImpl implements ClasseToxicologiaUseCase {

    private final ClasseToxicologiaRepository grupoRepository;
    private final ClasseToxicologiaMapper grupoMapper;
    private final ClasseToxicologiaUseCaseValidateImpl grupoUseCaseValidate;

    public ClasseToxicologiaUseCaseImpl(ClasseToxicologiaRepository grupoRepository,
            ClasseToxicologiaUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(ClasseToxicologiaMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public ClasseToxicologiaDTO insert(ClasseToxicologiaDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        ClasseToxicologia grupo = grupoMapper.grupoDTOToClasseToxicologia(grupoDTO);
        ClasseToxicologia responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToClasseToxicologiaDTO(responde);
    }

    @Override
    public ClasseToxicologiaDTO update(ClasseToxicologiaDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        ClasseToxicologia grupo = grupoMapper.grupoDTOToClasseToxicologia(grupoDTO);
        ClasseToxicologia responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToClasseToxicologiaDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<ClasseToxicologia> deletedClasseToxicologia = this.grupoRepository.findById(id);
        return deletedClasseToxicologia.isEmpty();
    }

    @Override
    public ClasseToxicologiaDTO getById(int id) {

        Optional<ClasseToxicologia> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToClasseToxicologiaDTO(grupo.get());
    }

    @Override
    public List<ClasseToxicologiaDTO> getAll() {
        List<ClasseToxicologia> grupos = this.grupoRepository.findAll();
        return grupoMapper.listClasseToxicologiaToListClasseToxicologiaDTO(grupos);
    }

}
