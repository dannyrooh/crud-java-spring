package com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.model.PlantaDaninha;
import com.dannyrooh.matrizinsumos.auxiliares.dataprovider.repository.PlantaDaninhaRepository;
import com.dannyrooh.matrizinsumos.auxiliares.domain.dto.PlantaDaninhaDTO;
import com.dannyrooh.matrizinsumos.auxiliares.domain.mapper.PlantaDaninhaMapper;
import com.dannyrooh.matrizinsumos.auxiliares.domain.usecase.PlantaDaninhaUseCase;
import com.dannyrooh.matrizinsumos.auxiliares.domain.validate.impl.PlantaDaninhaUseCaseValidateImpl;
import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;

@Service
public class PlantaDaninhaUseCaseImpl implements PlantaDaninhaUseCase {

    private final PlantaDaninhaRepository grupoRepository;
    private final PlantaDaninhaMapper grupoMapper;
    private final PlantaDaninhaUseCaseValidateImpl grupoUseCaseValidate;

    public PlantaDaninhaUseCaseImpl(PlantaDaninhaRepository grupoRepository,
            PlantaDaninhaUseCaseValidateImpl grupoUseCaseValidate) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(PlantaDaninhaMapper.class);
        this.grupoUseCaseValidate = grupoUseCaseValidate;
    }

    @Override
    public PlantaDaninhaDTO insert(PlantaDaninhaDTO grupoDTO) {
        grupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        PlantaDaninha grupo = grupoMapper.grupoDTOToPlantaDaninha(grupoDTO);
        PlantaDaninha responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToPlantaDaninhaDTO(responde);
    }

    @Override
    public PlantaDaninhaDTO update(PlantaDaninhaDTO grupoDTO) {

        grupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        PlantaDaninha grupo = grupoMapper.grupoDTOToPlantaDaninha(grupoDTO);
        PlantaDaninha responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToPlantaDaninhaDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        grupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<PlantaDaninha> deletedPlantaDaninha = this.grupoRepository.findById(id);
        return deletedPlantaDaninha.isEmpty();
    }

    @Override
    public PlantaDaninhaDTO getById(int id) {

        Optional<PlantaDaninha> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToPlantaDaninhaDTO(grupo.get());
    }

    @Override
    public List<PlantaDaninhaDTO> getAll() {
        List<PlantaDaninha> grupos = this.grupoRepository.findAll();
        return grupoMapper.listPlantaDaninhaToListPlantaDaninhaDTO(grupos);
    }

}
