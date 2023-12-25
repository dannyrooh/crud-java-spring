package com.dannyrooh.matrizinsumos.grupo.domain.usecase.impl;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.dannyrooh.matrizinsumos.exception.WithIdNotFoundException;
import com.dannyrooh.matrizinsumos.exception.WithNameAlreadInformedException;
import com.dannyrooh.matrizinsumos.grupo.dataprovider.model.Grupo;
import com.dannyrooh.matrizinsumos.grupo.dataprovider.repository.GrupoRepository;
import com.dannyrooh.matrizinsumos.grupo.domain.dto.GrupoDTO;
import com.dannyrooh.matrizinsumos.grupo.domain.mapper.GrupoMapper;
import com.dannyrooh.matrizinsumos.grupo.domain.usecase.GrupoUseCase;
import com.dannyrooh.matrizinsumos.grupo.domain.validate.GrupoUseCaseValidate;

@Service
public class GrupoUseCaseImpl implements GrupoUseCase {

    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    public GrupoUseCaseImpl(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = Mappers.getMapper(GrupoMapper.class);
    }

    @Override
    public GrupoDTO insert(GrupoDTO grupoDTO) {
        GrupoUseCaseValidate.validateInsert(grupoDTO);

        if (grupoRepository.existsByNomeIgnoreCase(grupoDTO.getNome())) {
            throw new WithNameAlreadInformedException();
        }

        Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
        Grupo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGrupoDTO(responde);
    }

    @Override
    public GrupoDTO update(GrupoDTO grupoDTO) {

        GrupoUseCaseValidate.validateUpdate(grupoDTO);

        if (!grupoRepository.existsById(grupoDTO.getId())) {
            throw new WithIdNotFoundException();
        }

        Integer existingGroupId = grupoRepository.findIdByNomeIgnoreCaseAndIdNot(grupoDTO.getNome(), grupoDTO.getId());

        if (existingGroupId != null) {
            throw new WithNameAlreadInformedException();
        }

        Grupo grupo = grupoMapper.grupoDTOToGrupo(grupoDTO);
        Grupo responde = this.grupoRepository.save(grupo);
        return grupoMapper.grupoToGrupoDTO(responde);
    }

    @Override
    public Boolean delete(int id) {
        GrupoUseCaseValidate.validateDelete(id);

        if (!grupoRepository.existsById(id)) {
            throw new WithIdNotFoundException();
        }

        this.grupoRepository.deleteById(id);
        Optional<Grupo> deletedGrupo = this.grupoRepository.findById(id);
        return deletedGrupo.isEmpty();
    }

    @Override
    public GrupoDTO getById(int id) {

        Optional<Grupo> grupo = grupoRepository.findById(id);

        if (!grupo.isPresent()) {
            throw new WithIdNotFoundException();
        }

        return grupoMapper.grupoToGrupoDTO(grupo.get());
    }

    @Override
    public List<GrupoDTO> getAll() {
        List<Grupo> grupos = this.grupoRepository.findAll();
        return grupoMapper.listGrupoToListGrupoDTO(grupos);
    }

}
