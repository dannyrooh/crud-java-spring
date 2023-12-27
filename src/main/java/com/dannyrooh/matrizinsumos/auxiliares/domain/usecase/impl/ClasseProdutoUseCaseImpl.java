package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.ClasseProduto;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.ClasseProdutoRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.ClasseProdutoDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.ClasseProdutoMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.ClasseProdutoUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.ClasseProdutoUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class ClasseProdutoUseCaseImpl implements ClasseProdutoUseCase {

    private final ClasseProdutoRepository grupoRepository;
    private final ClasseProdutoMapper grupoMapper;
    private final ClasseProdutoUseCaseValidateImpl grupoUseCaseValidate;

    public ClasseProdutoUseCaseImpl(ClasseProdutoRepository grupoRepository,
            ClasseProdutoUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(ClasseProdutoMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public ClasseProdutoDTO insert(ClasseProdutoDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        ClasseProduto grupo = grupoMapper.grupoDTOToClasseProduto(grupoDTO);
        ClasseProduto responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToClasseProdutoDTO(responde);
    }

    @Override
    public ClasseProdutoDTO update(ClasseProdutoDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        ClasseProduto grupo = grupoMapper.grupoDTOToClasseProduto(grupoDTO);
        ClasseProduto responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToClasseProdutoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<ClasseProduto> deletedClasseProduto = this.grupoRepository.findById(id);
        return deletedClasseProduto.isEmpty();
    }

    @Override
    public ClasseProdutoDTO getById(int id) {

        Optional<ClasseProduto> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToClasseProdutoDTO(grupo.get());
    }

    @Override
    public List<ClasseProdutoDTO> getAll() {
        List<ClasseProduto> grupos = this.grupoRepository.findAll();
        return grupoMapper.listClasseProdutoToListClasseProdutoDTO(grupos);
    }

}
